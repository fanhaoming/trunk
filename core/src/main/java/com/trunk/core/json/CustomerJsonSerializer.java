package com.trunk.core.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fanhaoming
 * @ClassName CustomerJsonSerializer
 * @Description TODO
 * @Date 2019/8/12 16:56
 * @Version
 **/
public class CustomerJsonSerializer {
    ObjectMapper mapper = null;
    JacksonJsonFilter jacksonFilter = null;

    public CustomerJsonSerializer(){
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new CustomBeanSerializerModifier()));
        jacksonFilter = new JacksonJsonFilter();
    }

    /**
     * 指定类输出字段
     * @param clazz 过滤的class
     * @param includes 包含的字段
     */
    public void include(Class<?> clazz, String includes) {
        if (clazz == null)
            return;
        if (StringUtils.isNotBlank(includes)) {
            jacksonFilter.include(clazz, includes.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    /**
     * 指定类过滤字段
     * @param clazz 过滤的class
     * @param ignores 过滤的字段
     */
    public void ignore(Class<?> clazz, String ignores) {
        if (clazz == null)
            return;
        if (StringUtils.isNotBlank(ignores)) {
            jacksonFilter.filter(clazz, ignores.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    /**
     * 自定义过滤字段的json序列化
     * @param object 序列化的类型
     * @return 序列化的结果
     * @throws JsonProcessingException
     */
    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        return mapper.writeValueAsString(object);
    }

}
