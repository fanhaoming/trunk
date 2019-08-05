package com.trunk.core.quartz;

import com.trunk.core.base.BaseServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author fanhaoming
 * @ClassName TaskService
 * @Description TODO
 * @Date 2019/8/5 9:09
 * @Version
 **/
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    Scheduler schedulerFactory;

    @PostConstruct
    @Override
    public void initSchedule() throws Exception {
        // 这里获取任务信息数据
        List<Task> jobList = listByCondition(new TaskCondition());
        for (Task task : jobList) {
            if (JobStatusEnum.RUNNING.getValue()==task.getJobStatus()) {
                addJob(task);
            }
        }
    }
    @Override
    public void addJob(Task task) throws Exception{
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(task.getJobName(), task.getJobGroup())// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup())// 触发器key
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
        schedulerFactory.scheduleJob(jobDetail, trigger);
            // 启动
            if (!schedulerFactory.isShutdown()) {
                schedulerFactory.start();
            }
    }
    @Override
    public void saveJob(Task task) {
        try {
            if(task.getJobStatus() == JobStatusEnum.RUNNING.getValue()){
                addJob(task);
            }
        } catch (Exception e) {
            task.setJobStatus(JobStatusEnum.FAIL.getValue());
            e.printStackTrace();
        }
        taskMapper.insert(task);
    }


    @Override
    public void pauseJob(String jobId) {
        try {
            Task task = taskMapper.findById(jobId);
            if(task != null){
                task.setJobStatus(JobStatusEnum.STOP.getValue());
                taskMapper.updateById(task);
                //暂停一个job
                JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
                schedulerFactory.pauseJob(jobKey);
            }
        }catch (Exception e){
        }
    }

    /**
     * 恢复一个定时任务
     * @param jobId
     */
    @Override
    public void resumeJob(String jobId) {
        try{
            //修改定时任务状态
            Task task = taskMapper.findById(jobId);
            if(task.getJobStatus() == JobStatusEnum.STOP.getValue()){
                task.setJobStatus(JobStatusEnum.RUNNING.getValue());
                taskMapper.updateById(task);
            }
            //恢复一个定时任务
            JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
            schedulerFactory.resumeJob(jobKey);
        }catch (Exception e){
        }
    }


    /**
     * 立即执行一个定时任务
     * @param jobId
     */
    @Override
    public void runOnce(String jobId) {
        try{
            Task task = taskMapper.findById(jobId);
            JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
            schedulerFactory.triggerJob(jobKey);
        }catch (Exception e){
        }
    }


    /**
     * 更新时间表达式
     * @param id
     * @param cronExpression
     */
    @Override
    public void updateCron(String id, String cronExpression) {
        try {
            Task task = taskMapper.findById(id);
            if(task != null){
                task.setCronExpression(cronExpression);
                taskMapper.updateById(task);
                TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getJobGroup());
                CronTrigger trigger = (CronTrigger) schedulerFactory.getTrigger(triggerKey);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                schedulerFactory.rescheduleJob(triggerKey,trigger);
            }
        }catch(Exception e){
        }

    }


}
