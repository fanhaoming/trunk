package com.trunk.support.controllers;

import com.trunk.core.json.JsonFilter;
import com.trunk.core.utils.RedisUtils;
import com.trunk.core.web.ResponseResult;
import com.trunk.support.condition.MenuCondition;
import com.trunk.support.condition.UserCondition;
import com.trunk.support.entity.User;
import com.trunk.support.service.MenuService;
import com.trunk.support.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Api(tags="菜单模块")
@RestController
@RequestMapping("/menu")
public class MenuControl {

    @Autowired
    MenuService menuServiceImpl;


    @RequestMapping(produces = "application/json")
    @ApiOperation(value = "获取菜单列表",httpMethod="POST")
    @JsonFilter(type=User.class,filter = "pwd")
    public ResponseResult test(@RequestBody MenuCondition condition) {
        return ResponseResult.ok(data -> {
            data.put("result",menuServiceImpl.listByCondition(condition));
        });
    }


}
