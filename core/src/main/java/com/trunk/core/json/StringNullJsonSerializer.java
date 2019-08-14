package com.trunk.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author fanhaoming
 * @ClassName StringNullJsonSerializer
 * @Description TODO
 * @Date 2019/8/12 16:45
 * @Version
 **/

public class StringNullJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            gen.writeString("");
        } else {
            gen.writeObject(value);
        }
    }


}