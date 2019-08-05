package com.trunk.core.quartz;

import com.trunk.core.enums.IBaseEnum;

/**
 * Created by Administrator on 2019/8/5.
 */
public enum JobStatusEnum implements IBaseEnum{
    STOP(0),
    RUNNING(1),
    FAIL(2);


    private int value;

    JobStatusEnum(int value) {
        this.value = value;
    }


}
