package com.trunk.core.quartz;

import com.trunk.core.quartz.condition.CronTaskCondition;
import com.trunk.core.quartz.entity.CronTask;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author fanhaoming
 * @ClassName ITaskService
 * @Description TODO
 * @Date 2019/8/5 9:28
 * @Version
 **/
public interface TaskService  {

    @PostConstruct
    void initSchedule() ;

    void addJob(CronTask task);

    void pauseJob(String jobName, String jobGroup);

    void resumeJob(String jobName, String jobGroup);

    void runOnce(String jobName, String jobGroup);

    void updateCron(String jobName, String jobGroup, String cronExpression);

    void deleteCronTask(String jobName, String jobGroup);

    List<CronTask> listCronTasks(CronTaskCondition condition);

    CronTask findOne(String jobName, String jobGroup);
}
