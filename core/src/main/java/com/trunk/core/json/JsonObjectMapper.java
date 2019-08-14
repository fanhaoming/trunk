package com.trunk.core.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author fanhaoming
 * @ClassName JsonObjectMapper
 * @Description TODO
 * @Date 2019/8/12 16:39
 * @Version
 **/
public class JsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -189284858226396323L;

    public JsonObjectMapper() {
        super();
        // 空值处理为空串
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.setSerializerFactory(this.getSerializerFactory().withSerializerModifier(new CustomBeanSerializerModifier()));
    }

}