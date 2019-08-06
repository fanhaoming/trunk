package com.trunk.core.controllers;

import com.trunk.core.quartz.TaskService;
import com.trunk.core.quartz.condition.CronTaskCondition;
import com.trunk.core.quartz.entity.CronTask;
import com.trunk.core.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaoming
 * @ClassName TaskControl
 * @Description TODO
 * @Date 2019/8/5 10:49
 * @Version
 **/
@Api(tags="定时器模块")
@RestController
@RequestMapping("/task")
public class TaskControl {

    @Autowired
    TaskService taskServiceImpl;

    @RequestMapping(value = "addTask",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "添加定时任务",httpMethod="POST")
    public ResponseResult addTask(@RequestBody CronTask cronTask) {
        taskServiceImpl.addJob(cronTask);
        return ResponseResult.ok();
    }


    @RequestMapping(value = "resumeJob",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "恢复一个任务",httpMethod="POST")
    public ResponseResult resumeJob(String jobName, String jobGroup){
        taskServiceImpl.resumeJob(jobName,jobGroup);
        return ResponseResult.ok();
    }
    @RequestMapping(value = "pauseJob",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "暂停一个任务",httpMethod="POST")
    public ResponseResult pauseJob(String jobName, String jobGroup){
        taskServiceImpl.pauseJob(jobName,jobGroup);
        return ResponseResult.ok();
    }
    @RequestMapping(value = "runOnce",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "立即运行一个任务一次",httpMethod="POST")
    public ResponseResult runOnce(String jobName, String jobGroup){
        taskServiceImpl.runOnce(jobName,jobGroup);
        return ResponseResult.ok();
    }
    @RequestMapping(value = "updateCron",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "更新一个任务",httpMethod="POST")
    public ResponseResult updateCron(String jobName, String jobGroup,String cronExpression){
        taskServiceImpl.updateCron(jobName,jobGroup,cronExpression);
        return ResponseResult.ok();
    }
    @RequestMapping(value = "findOne",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "获取任务详情",httpMethod="POST")
    public ResponseResult findOne(String jobName, String jobGroup){
        return ResponseResult.ok(data -> {
            data.put("task",taskServiceImpl.findOne(jobName,jobGroup));
        });
    }

    @RequestMapping(value = "listCronTriggers",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "获取定时器列表",httpMethod="POST")
    public ResponseResult listCronTriggers(@RequestBody CronTaskCondition condition) throws Exception{
        return ResponseResult.ok(data -> {
            data.put("tasks",taskServiceImpl.listCronTasks(condition));
        });
    }

    @RequestMapping(value = "deleteCronTask",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "删除定时器任务",httpMethod="POST")
    public ResponseResult deleteCronTask(String jobName, String jobGroup) throws Exception{
        taskServiceImpl.deleteCronTask(jobName,jobGroup);
        return ResponseResult.ok();
    }
}
