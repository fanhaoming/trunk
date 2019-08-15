package com.trunk.core.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

/**
 * @author fanhaoming
 * @ClassName SecurityUtils
 * @Description TODO
 * @Date 2019/8/14 16:32
 * @Version
 **/
public class SecurityUtils {

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
