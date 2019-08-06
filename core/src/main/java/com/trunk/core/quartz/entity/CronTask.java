package com.trunk.core.quartz.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fanhaoming
 * @ClassName CronTriggers
 * @Description TODO
 * @Date 2019/8/6 10:48
 * @Version
 **/
@Getter
@Setter
public class CronTask {

    private String jobName;
    private String jobGroup;
    private String cronExpression;
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobClassName;
    private String timeZoneId;
    private String description;
    private String nextFireTime;
    private String prevFireTime;
    private String priority;
    private String triggerState;
    private String triggerType;
    private String startTime;
    private String endTime;
    private String calendarName;
    private String misfireInstr;
    private String jobData;
    private String isNonconcurrent;
}
