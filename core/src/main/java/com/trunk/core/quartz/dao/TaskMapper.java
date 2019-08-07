package com.trunk.core.quartz.dao;


import com.trunk.core.quartz.entity.CronTask;
import com.trunk.core.query.ConditionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName TaskMapper
 * @Description TODO
 * @Date 2019/8/5 9:31
 * @Version
 **/
@Mapper
public interface TaskMapper  {
    List<CronTask> listCronTasks(ConditionQuery conditionQuery);

    CronTask findOne(@Param("jobName") String jobName,@Param("jobGroup") String jobGroup);
}
