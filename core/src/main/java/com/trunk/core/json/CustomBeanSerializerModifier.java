package com.trunk.core.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.trunk.core.dictionary.DictJsonAnnotation;

import java.util.Date;
import java.util.List;

/**
 * @author fanhaoming
 * @ClassName CustomBeanSerializerModifier
 * @Description TODO
 * @Date 2019/8/12 16:40
 * @Version
 **/
public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> _stringNullJsonSerializer = new StringNullJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            // 判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isStringType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullJsonSerializer());
            } else if (isDateType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullJsonSerializer());
            } else if (isFloatType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullJsonSerializer());
            } else if (isIntegerType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullJsonSerializer());
            }


            DictJsonAnnotation annotation = writer.getAnnotation(DictJsonAnnotation.class);
            if (annotation != null) {
                DictJsonSerializer jsonSerializer = DictJsonSerializerInstance.getInstance().getByAnnoation(annotation);
                writer.assignSerializer(jsonSerializer);
            }
        }
        return beanProperties;
    }

    protected boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(String.class);
    }

    protected boolean isDateType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Date.class);
    }

    protected boolean isFloatType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Float.class);
    }

    protected boolean isIntegerType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Integer.class);
    }


    protected JsonSerializer<Object> defaultNullJsonSerializer() {
        return _stringNullJsonSerializer;
    }
}

