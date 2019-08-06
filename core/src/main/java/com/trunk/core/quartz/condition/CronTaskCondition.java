package com.trunk.core.quartz.condition;

import com.trunk.core.base.AbstractCondition;
import com.trunk.core.query.Query;
import com.trunk.core.query.QueryBuild;
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
public class CronTaskCondition extends AbstractCondition{
    @Query
    private String jobName;
    @Query
    private String jobGroup;
    @Query
    private String cronExpression;
    @Query
    private String jobClassName;



}
