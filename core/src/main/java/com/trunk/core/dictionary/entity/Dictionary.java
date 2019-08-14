package com.trunk.core.dictionary.entity;

import com.trunk.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName Dictionary
 * @Description TODO
 * @Date 2019/8/6 16:42
 * @Version
 **/
@Getter
@Setter
public class Dictionary extends BaseEntity{

    private String parentId;
    private String code;
    private String dictionaryTypeId;
    private boolean isEnabled;
    private String name;
    private List<Dictionary> dicts;
}
