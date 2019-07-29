package com.trunk.support.entity;

import com.trunk.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
@Getter
@Setter
public class User extends BaseEntity{
    private String username;
    private String pwd;
    private String icon;
    private String nickname;

}
