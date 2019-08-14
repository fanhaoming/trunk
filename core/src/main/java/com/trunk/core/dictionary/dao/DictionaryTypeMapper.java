package com.trunk.core.dictionary.dao;

import com.trunk.core.base.BaseMapper;
import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.entity.DictionaryType;
import com.trunk.core.query.ConditionQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName DictionaryMapper
 * @Description TODO
 * @Date 2019/8/7 15:54
 * @Version
 **/
@Mapper
public interface DictionaryTypeMapper extends BaseMapper<DictionaryType> {

    List<DictionaryType> findDicttypeAndDict();
}
