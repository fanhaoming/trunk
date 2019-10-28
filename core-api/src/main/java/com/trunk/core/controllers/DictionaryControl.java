package com.trunk.core.controllers;

import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.entity.DictionaryType;
import com.trunk.core.dictionary.service.DictionaryService;
import com.trunk.core.dictionary.service.DictionaryTypeService;
import com.trunk.core.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName DictionaryControl
 * @Description TODO
 * @Date 2019/8/7 12:05
 * @Version
 **/
@Api("数据字典")
@RestController
@RequestMapping
public class DictionaryControl {

    @Autowired
    DictionaryTypeService dictionaryTypeServiceImpl;
    @Autowired
    DictionaryService dictionaryServiceImpl;

    @RequestMapping(value = "findDictionaryType",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "获取数据字典类",httpMethod="POST")
    public List<DictionaryType> findDictionaryType(){
        return dictionaryTypeServiceImpl.loadDictionaryType();
    }


    @RequestMapping(value = "saveDict",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "保存数据字典",httpMethod="POST")
    public Dictionary saveDict(@RequestBody Dictionary dictionary){
        dictionaryServiceImpl.insert(dictionary);
        return dictionary;
    }

    @RequestMapping(value = "clearCache",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "清除缓存",httpMethod="POST")
    public ResponseResult clearCache(){
        dictionaryTypeServiceImpl.clearDictCache();
        return ResponseResult.ok();
    }


    @RequestMapping(value = "findByTypeCodeAndValue",produces = "application/json",method = RequestMethod.POST)
    @ApiOperation(value = "保存数据字典",httpMethod="POST")
    public ResponseResult findByTypeCodeAndValue(String typeCode,String value)throws  Exception{
        Dictionary d = dictionaryTypeServiceImpl.findDictionaryById(typeCode,value);
        return ResponseResult.ok(data -> {
            data.put("result",d);
        });
    }
}
