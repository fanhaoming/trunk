package com.trunk.core.dictionary.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.trunk.core.base.BaseServiceImpl;
import com.trunk.core.dictionary.condition.DictionaryCondition;
import com.trunk.core.dictionary.condition.DictionaryTypeCondition;
import com.trunk.core.dictionary.dao.DictionaryMapper;
import com.trunk.core.dictionary.dao.DictionaryTypeMapper;
import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.entity.DictionaryType;
import com.trunk.core.dictionary.service.DictionaryTypeService;
import com.trunk.core.query.ConditionQuery;
import com.trunk.core.utils.ListCheckCondition;
import com.trunk.core.utils.ListUtils;
import com.trunk.core.utils.RedisUtils;
import com.trunk.core.utils.TreeOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fanhaoming
 * @ClassName DictionaryServiceImpl
 * @Description TODO
 * @Date 2019/8/7 11:59
 * @Version
 **/
@Service
public class DictionaryTypeServiceImpl extends BaseServiceImpl<DictionaryType> implements DictionaryTypeService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DictionaryMapper dictionaryMapper;
    @Autowired
    DictionaryTypeMapper dictionaryTypeMapper;

    @Override
    public void cacheDictionary() {
        List<DictionaryType> dictionaryTypes = dictionaryTypeMapper.findDicttypeAndDict();
        if(dictionaryTypes != null && dictionaryTypes.size()>=0) {
            System.out.println(redisUtils.hmset("dictionaryType",dictionaryTypes.stream().collect(Collectors.toMap(key->key.getCode(),value->value.getDicts().stream().collect(Collectors.toMap(key1->key1.getId(),value1->value1))))));
        }
    }

    @Override
    public Map<String,Dictionary> findDictCache(String typeCode) {
        Long dicSize = redisUtils.hsize("dictionaryType");
        if(dicSize == 0) {
            synchronized (DictionaryTypeServiceImpl.class) {
                dicSize = redisUtils.hsize("dictionaryType");
                if(dicSize == 0){
                    cacheDictionary();
                    dicSize = redisUtils.hsize("dictionaryType");
                }
            }
        }
        return (Map<String, Dictionary>) redisUtils.hget("dictionaryType",typeCode);
    }

    @Override
    public Dictionary findDictionaryById(String typeCode, String value) {
        Map<String,Dictionary> dictionaryMap = findDictCache(typeCode);
        if(dictionaryMap==null)
            return null;
        return dictionaryMap.get(value);
    }

    @Override
    public void clearDictCache(Object... obj) {
        redisUtils.hclear("dictionary",obj);
    }

    @Override
    public List<DictionaryType> findEnableDictTree() {
        List<DictionaryType> dicts = (List<DictionaryType>) redisUtils.hget("dictionary","enableDic");
        if(dicts==null) {
            synchronized (this){
                if(dicts==null) {
                    dicts = loadDictionaryType();
                    redisUtils.hset("dictionary","enableDic",dicts);
                }
            }
        }
        return dicts;
    }



    @Override
    public List<DictionaryType> loadDictionaryType() {
        DictionaryTypeCondition contidion = new DictionaryTypeCondition();
        contidion.setIsEnabled(1);
        List<DictionaryType> dictionaryTypes = dictionaryTypeMapper.listByCondition(new ConditionQuery(contidion));

        if(dictionaryTypes != null && dictionaryTypes.size()>0){

            List<Dictionary> dictionaries = dictionaryMapper.listByCondition(new ConditionQuery(new DictionaryCondition()));

            List<Dictionary> rootDicts = new ArrayList<>();

            List<Dictionary> tempDicts = new ArrayList<>(dictionaries);

            dictionaries.forEach(data->{
                if(StringUtils.isBlank(data.getParentId())){
                    rootDicts.add(data);
                    tempDicts.remove(data);
                }
            });

            rootDicts.forEach(rootDict->{
                rootDict.setDicts(ListUtils.loadTreeByRecursion(rootDict,tempDicts, new TreeOperation<Dictionary>() {
                    @Override
                    public Boolean check(Dictionary t1, Dictionary t2) {
                        return t1.getId().equals(t2.getParentId());
                    }
                    @Override
                    public void put(Dictionary dictionary, List<Dictionary> list) {
                        dictionary.setDicts(list);
                    }
                }));
            });


            dictionaryTypes.forEach(dictionaryType->{
                if(dictionaryType.isEnabled()){
                    if(dictionaryType.getDicts()==null){
                        dictionaryType.setDicts(new ArrayList<>());
                    }
                    dictionaryType.getDicts().addAll(ListUtils.findAll(rootDicts,dict ->{
                        return dictionaryType.getId().equals(dict.getDictionaryTypeId());
                    }));
                }
            });
        }
        return dictionaryTypes;
    }

}
