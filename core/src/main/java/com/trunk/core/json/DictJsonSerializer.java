package com.trunk.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.trunk.core.dictionary.DictJsonAnnotation;
import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.service.DictionaryTypeService;
import com.trunk.core.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.trunk.core.web.SpringContextHolder;

import java.io.IOException;

public class DictJsonSerializer extends JsonSerializer<Object> {

	private DictJsonAnnotation dictJsonAnnotation;

	public void setDictJsonAnnoation(DictJsonAnnotation dictJsonAnnotation) {
		this.dictJsonAnnotation = dictJsonAnnotation;
	}

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		DictionaryTypeService dictionaryTypeService = SpringContextHolder.getBean(DictionaryTypeServiceImpl.class);
		Dictionary dictionary = dictionaryTypeService.findDictionaryById(dictJsonAnnotation.code(), value.toString());
		if (dictionary != null) {
			
			gen.writeStartObject();
			gen.writeStringField("id", dictionary.getId());
			gen.writeStringField("name", dictionary.getName());
			gen.writeStringField("code", dictionary.getCode());
			gen.writeEndObject();
		} else {
			gen.writeStartObject();
			gen.writeEndObject();
		}
	}

}
