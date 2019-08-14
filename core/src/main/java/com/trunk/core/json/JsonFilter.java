package com.trunk.core.json;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(JsonFilters.class)
public @interface JsonFilter {
	
	Class<?> type();

	String include() default "";

	String filter() default "";
}