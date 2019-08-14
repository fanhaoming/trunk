package com.trunk.support.entity;

import com.trunk.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fanhaoming
 * @ClassName Menu
 * @Description TODO
 * @Date 2019/8/14 9:52
 * @Version
 **/
@Getter
@Setter
public class Menu  extends BaseEntity {
    private String name;
    private String parentId;
    private boolean isEnable;
    private String icon;
    private Integer sort;

}
