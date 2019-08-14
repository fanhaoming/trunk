package com.trunk.core.dictionary.condition;

import com.trunk.core.base.AbstractCondition;
import com.trunk.core.query.Query;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fanhaoming
 * @ClassName DictionaryTypeCondition
 * @Description TODO
 * @Date 2019/8/7 16:11
 * @Version
 **/
@Getter
@Setter
public class DictionaryTypeCondition extends AbstractCondition {
    @Query
    private int isEnabled = 1;
}
