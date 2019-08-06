package com.trunk.core.quartz;

import com.fasterxml.jackson.annotation.JsonValue;
import com.trunk.core.enums.IBaseEnum;
import com.trunk.core.reflect.GetField;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2019/8/5.
 */
public enum JobStatusEnum {
    ACQUIRED("ACQUIRED"),
    WAITING("WAITING"),
    PAUSED("PAUSED"),
    BLOCKED("BLOCKED"),
    ERROR("ERROR");


    private String value;

    JobStatusEnum(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        Field field = GetField.getInstance().getByName(this.getClass(), "value");
        if (field == null)
            return "";

        try {
            return (String)field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
