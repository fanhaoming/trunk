package com.trunk.support.controllers;

import com.trunk.core.annotation.TestAnnotation;
import com.trunk.core.json.JsonFilter;
import com.trunk.core.listeners.LoadPropertiesListener;
import com.trunk.core.utils.RedisUtils;
import com.trunk.core.validate.Captcha;
import com.trunk.core.validate.GifCaptcha;
import com.trunk.core.validate.JpgCaptcha;
import com.trunk.core.web.ResponseResult;
import com.trunk.support.condition.UserCondition;
import com.trunk.support.entity.User;
import com.trunk.support.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Api(tags="测试功能模块")
@RestController
@RequestMapping("/test")
/*@TestAnnotation*/
@Log
public class TestControl {



    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户",httpMethod="GET")
    public ResponseResult test() {
        return ResponseResult.ok();
    }

    @RequestMapping(value="/test2",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户2",httpMethod="GET")
    public ResponseResult test2() {
        return ResponseResult.ok();
    }


    @RequestMapping(value = "gifCaptcha",method= RequestMethod.GET)
    public void getGifCaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception{
        //告诉客户端，输出的格式
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        new GifCaptcha(200,80).out(response.getOutputStream());

    }
    @RequestMapping(value = "/captcha")
    public void imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        new JpgCaptcha(200,80).out(response.getOutputStream());
    }
}
