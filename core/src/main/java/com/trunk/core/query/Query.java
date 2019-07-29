package com.trunk.core.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Query {

	String type() default QueryBuild.EQUAL_QUERY_TYPE;
	String column() default "";
	boolean isMinDate() default false;
	boolean isMaxDate() default false;
}
