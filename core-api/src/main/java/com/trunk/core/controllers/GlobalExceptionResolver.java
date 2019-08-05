package com.trunk.core.controllers;

import com.trunk.core.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fanhaoming
 * @ClassName G
 * @Description TODO
 * @Date 2019/8/1 14:59
 * @Version
 **/
@ControllerAdvice
public class GlobalExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult handleException(Exception e) {
        return ResponseResult.error();
    }


}