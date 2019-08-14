package com.trunk.core.dictionary.entity;

import com.trunk.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName DictionaryType
 * @Description TODO
 * @Date 2019/8/7 10:15
 * @Version
 **/
@Getter
@Setter
public class DictionaryType extends BaseEntity {
    private String code;
    private boolean isEnabled;
    private String name;
    private List<Dictionary> dicts;
}
