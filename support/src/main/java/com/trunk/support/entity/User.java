package com.trunk.support.entity;

import com.trunk.core.base.BaseEntity;
import com.trunk.core.annotation.DictJsonAnnotation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Getter
@Setter
public class User extends BaseEntity implements UserDetails,CredentialsContainer{
    private String username;
    private String password;
    private String icon;
    private String nickname;

    @DictJsonAnnotation(code = "cardType")
    private String type;

    @Override
    public void eraseCredentials() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
