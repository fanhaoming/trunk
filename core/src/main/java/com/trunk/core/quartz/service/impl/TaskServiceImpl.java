package com.trunk.core.quartz.service.impl;

import com.trunk.core.quartz.condition.CronTaskCondition;
import com.trunk.core.quartz.dao.TaskMapper;
import com.trunk.core.quartz.entity.CronTask;
import com.trunk.core.quartz.service.TaskService;
import com.trunk.core.query.ConditionQuery;
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
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    Scheduler scheduler;
    @PostConstruct
    @Override
    public void initSchedule() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addJob(CronTask cronTask) {
        try {
        // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(cronTask.getJobClassName()).newInstance()
                    .getClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(cronTask.getJobName(), cronTask.getJobGroup())// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(cronTask.getJobName(), cronTask.getJobGroup())// 触发器key
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronTask.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复一个定时任务
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) {
        try{
            //恢复一个定时任务
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.resumeJob(jobKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 立即执行一个定时任务
     */
    @Override
    public void runOnce(String jobName, String jobGroup) {
        try{
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.triggerJob(jobKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


  /**
     * 更新时间表达式
     */
    @Override
    public void updateCron(String jobName, String jobGroup, String cronExpression) {
        try {
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey,trigger);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCronTask(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));// 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CronTask> listCronTasks(CronTaskCondition condition) {
        return taskMapper.listCronTasks(new ConditionQuery(condition));
    }

    @Override
    public CronTask findOne(String jobName, String jobGroup) {
        return taskMapper.findOne(jobName,jobGroup);
    }


}
