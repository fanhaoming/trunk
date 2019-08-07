package com.trunk.support.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author fanhaoming
 * @ClassName HelloWorldJob
 * @Description TODO
 * @Date 2019/8/5 9:10
 * @Version
 **/
@DisallowConcurrentExecution //作业不并发
@Component
public class HelloWorldJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println("欢迎使用yyblog,这是一个定时任务  --小卖铺的老爷爷!"+ new Date());

    }

}