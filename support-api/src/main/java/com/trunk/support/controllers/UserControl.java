package com.trunk.support.controllers;

import com.trunk.core.json.JsonFilter;
import com.trunk.core.web.ResponseResult;
import com.trunk.support.condition.UserCondition;
import com.trunk.support.entity.User;
import com.trunk.support.service.UserService;
import com.trunk.core.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Api(tags="用户模块")
@RestController
@RequestMapping("/user")
public class UserControl {

    @Autowired
    UserService userServiceImpl;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(produces = "application/json")
    @ApiOperation(value = "获取用户",httpMethod="POST")
    @JsonFilter(type=User.class,filter = "pwd")
    public ResponseResult test(@RequestBody UserCondition userCondition) {
        return ResponseResult.ok(data -> {
            data.put("result",userServiceImpl.listByCondition(userCondition));
        });
    }


}
