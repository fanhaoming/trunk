package com.trunk.core.controllers;

import com.trunk.core.quartz.TaskService;
import com.trunk.core.quartz.Task;
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
    public ResponseResult addTask(@RequestBody Task task){
        taskServiceImpl.saveJob(task);
        return ResponseResult.ok();
    }
}
