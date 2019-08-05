package com.trunk.core.quartz;

import com.trunk.core.base.BaseService;
import com.trunk.core.quartz.Task;
import org.quartz.SchedulerException;


/**
 * @author fanhaoming
 * @ClassName ITaskService
 * @Description TODO
 * @Date 2019/8/5 9:28
 * @Version
 **/
public interface TaskService extends BaseService<Task> {

    void initSchedule() throws SchedulerException, Exception;

    void addJob(Task task) throws Exception;

    void saveJob(Task task);

    void pauseJob(String jobId);

    void resumeJob(String jobId);

    void runOnce(String jobId);

    void updateCron(String id, String cronExpression);
}
