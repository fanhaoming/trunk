package com.trunk.support.service.impl;

import com.trunk.support.dao.UserMapper;
import com.trunk.support.entity.User;
import com.trunk.support.service.UserService;
import com.trunk.core.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService,UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User dbUser = userMapper.findByUsername(s);
        User user = new User(s,
                dbUser.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,role"));
        return user;
    }

    @Override
    public User insert(User entity) {
        if(StringUtils.isNotBlank(entity.getUsername()) && StringUtils.isNotBlank(entity.getPassword())) {
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            entity.setPassword(bc.encode(entity.getPassword()));
            return super.insert(entity);
        }
        return null;
    }
}
