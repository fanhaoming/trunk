package com.trunk.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.trunk.core.reflect.GetField;

import java.lang.reflect.Field;

public interface IBaseEnum {

	String DEFAULT_VALUE_NAME = "value";

	@JsonValue
	default int getValue() {
		Field field = GetField.getInstance().getByName(this.getClass(), "value");
		if (field == null)
			return 0;

		try {
			return (int)field.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer value) {
		if (value == null)
			throw new IllegalArgumentException("DisplayedEnum value should not be null");
		if (enumClass.isAssignableFrom(IBaseEnum.class))
			throw new IllegalArgumentException("illegal DisplayedEnum type");
		T[] enums = enumClass.getEnumConstants();
		for (T t : enums) {
			IBaseEnum baseEnum = (IBaseEnum) t;
			if (baseEnum.getValue() == value)
				return (T) baseEnum;
		}
		throw new IllegalArgumentException("cannot parse integer: " + value + " to " + enumClass.getName());
	}
}
