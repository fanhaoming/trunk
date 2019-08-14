package com.trunk.core.dictionary.service;

import com.trunk.core.base.BaseService;
import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.entity.DictionaryType;

import java.util.List;
import java.util.Map;

/**
 * @author fanhaoming
 * @ClassName DictionaryService
 * @Description TODO
 * @Date 2019/8/7 11:59
 * @Version
 **/
public interface DictionaryTypeService extends BaseService<DictionaryType>{
    void cacheDictionary();

    Map<String,Dictionary> findDictCache(String typeCode);

    Dictionary findDictionaryById(String typeCode, String value);


    void clearDictCache(Object... obj);

    List<DictionaryType> findEnableDictTree();

    List<DictionaryType> loadDictionaryType();
}
