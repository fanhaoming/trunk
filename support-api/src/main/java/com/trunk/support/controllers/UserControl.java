package com.trunk.support.controllers;

import com.trunk.core.annotation.TestAnnotation;
import com.trunk.core.json.JsonFilter;
import com.trunk.core.listeners.LoadPropertiesListener;
import com.trunk.core.web.ResponseResult;
import com.trunk.support.condition.UserCondition;
import com.trunk.support.entity.User;
import com.trunk.support.service.UserService;
import com.trunk.core.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Api(tags="用户模块")
@RestController
@RequestMapping("/user")
public class UserControl{

    @Autowired
    UserService userServiceImpl;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(produces = "application/json")
    @ApiOperation(value = "获取用户",httpMethod="POST")
    @JsonFilter(type=User.class,filter = "pwd")
    public ResponseResult findUser(@RequestBody UserCondition userCondition) {
        return ResponseResult.ok(data -> {
            data.put("result",userServiceImpl.listByCondition(userCondition));
        });
    }
    @RequestMapping(value = "insert",produces = "application/json")
    @ApiOperation(value = "新增用户",httpMethod="POST")
    public ResponseResult insert(@RequestBody User user) {
        return ResponseResult.ok(data -> {
            data.put("result",userServiceImpl.insert(user));
        });
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户",httpMethod="GET")
    @TestAnnotation
    public ResponseResult test() {
        return ResponseResult.ok();
    }

    @RequestMapping(value="/test2",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户2",httpMethod="GET")
    public ResponseResult test2() {
        return ResponseResult.ok();
    }
}
