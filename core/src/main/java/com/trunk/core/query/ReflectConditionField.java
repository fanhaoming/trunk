package com.trunk.core.query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class ReflectConditionField {

	private static ReflectConditionField instance;

	public static ReflectConditionField getInstance() {
		synchronized (ReflectConditionField.class) {
			if (instance == null) {
				instance = new ReflectConditionField();
				instance.init();
			}
		}
		return instance;
	}

	static HashMap<String, List<FieldAnnotation>> map;

	private void init() {
		map = new HashMap<String, List<FieldAnnotation>>();
	}

	@SuppressWarnings("rawtypes")
	public <T extends Annotation> List<FieldAnnotation> traverseFieldAnnoation(Class clazz, Class<T> annotationClazz) {

		String key = clazz.getPackage() + "." + clazz.getName() + "@" + annotationClazz.getName();
		if (map.containsKey(key)) {
			return map.get(key);
		}

		List<FieldAnnotation> fieldAnnotations = new ArrayList<FieldAnnotation>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			T annotation = field.getAnnotation(annotationClazz);
			if (annotation != null) {
				fieldAnnotations.add(new FieldAnnotation(field, annotation));
			}
		}
		
		while(true){			
			clazz = clazz.getSuperclass();
			if(clazz.equals(Object.class)){
				break;
			}
			
			fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				T annotation = field.getAnnotation(annotationClazz);
				if (annotation != null) {
					fieldAnnotations.add(new FieldAnnotation(field, annotation));
				}
			}
		}
		map.put(key,fieldAnnotations);
		return fieldAnnotations;
	}

	class FieldAnnotation {
		private Field field;
		private Annotation annotation;

		public FieldAnnotation(Field field, Annotation annotation) {
			this.field = field;
			this.annotation = annotation;
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		public Annotation getAnnotation() {
			return annotation;
		}

		public void setAnnotation(Annotation annotation) {
			this.annotation = annotation;
		}

	}

}
