package com.trunk.support.service.impl;

import com.trunk.support.dao.UserMapper;
import com.trunk.support.entity.User;
import com.trunk.support.service.IUserService;
import com.trunk.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    UserMapper userMapper;
}
