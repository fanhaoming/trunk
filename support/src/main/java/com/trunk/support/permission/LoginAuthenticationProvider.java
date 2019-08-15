package com.trunk.support.permission;

import com.trunk.support.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fanhaoming
 * @ClassName LoginAuthenticationProvider
 * @Description TODO
 * @Date 2019/8/15 11:46
 * @Version
 **/
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这里通过我们自定义的对象，取出收集的表单参数进行逻辑验证
        LoginWebAuthenticationDetails details = (LoginWebAuthenticationDetails) authentication.getDetails();
        String encryptedClientRandom = details.getEncryptedClientRandom();
        String username = details.getUsername();
        details.getPassword();
        // 这里是验证逻辑
        if (true) {
            throw new DisabledException("用户名或者密码不正确！");
        }
        // 授权
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        List<GrantedAuthority> list = null;//globalPermissionService.getUserGrantedAuthority(username);
        grantedAuths.addAll(list);
        // 验证成功 返回这个对象
        return new UsernamePasswordAuthenticationToken(username, "", grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

