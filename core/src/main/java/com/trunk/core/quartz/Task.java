package com.trunk.core.quartz;

import com.trunk.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fanhaoming
 * @ClassName TaskDO
 * @Description TODO
 * @Date 2019/8/5 9:12
 * @Version
 **/
@Getter
@Setter
public class Task extends BaseEntity {
    private String jobName;
    private String description;
    private String cronExpression;
    private String beanClass;
    private Integer jobStatus;
    private String jobGroup;

}
