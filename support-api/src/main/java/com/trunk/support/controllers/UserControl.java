package com.trunk.support.controllers;

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

    @RequestMapping
    @ResponseBody
    @ApiOperation(value = "查看用户",httpMethod="POST")
    public List<User> index(@RequestBody UserCondition userCondition) throws Exception {
        if(true){
            throw new Exception("");
        }
        return userServiceImpl.listByCondition(userCondition);
    }


}
