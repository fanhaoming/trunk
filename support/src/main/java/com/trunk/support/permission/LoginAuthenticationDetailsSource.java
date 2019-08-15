package com.trunk.support.permission;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fanhaoming
 * @ClassName LoginAuthenticationDetailsSource
 * @Description TODO
 * @Date 2019/8/15 11:46
 * @Version
 **/
public class LoginAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

        @Override
        public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
            //DetailSource返回自定义的认证对象
            return new LoginWebAuthenticationDetails(context);
        }
}