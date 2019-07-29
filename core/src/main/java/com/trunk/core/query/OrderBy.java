package com.trunk.core.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.TYPE, ElementType.FIELD})//次注解作用于类和字段上  
public @interface OrderBy {

	String column() default "";
	int priority() default 0;
	
}
