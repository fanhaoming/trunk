package com.trunk.support;

import com.trunk.support.condition.UserCondition;
import com.trunk.support.entity.User;
import com.trunk.support.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2019/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
public class UserTest {
    @Autowired
    UserService userServiceImpl;

    @Test
    public void listByCondition(){
        List<User> users = userServiceImpl.listByCondition(new UserCondition());
    }

}
