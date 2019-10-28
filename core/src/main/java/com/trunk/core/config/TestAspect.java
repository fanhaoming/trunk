package com.trunk.core.config;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author fanhaoming
 * @ClassName TestAspect
 * @Description TODO
 * @Date 2019/10/28 15:00
 * @Version
 **/

@Component
@Aspect
@Log
public class TestAspect {

    @Pointcut("@within(com.trunk.core.annotation.TestAnnotation)||@annotation(com.trunk.core.annotation.TestAnnotation)")
    public void asAnnotation() {}


    @Before("asAnnotation()")
    public void beforeRun(JoinPoint joinPoint) {
        log.info("----------------------------------------");
    }

}
