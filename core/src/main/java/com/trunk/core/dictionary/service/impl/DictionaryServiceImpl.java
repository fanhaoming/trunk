package com.trunk.core.dictionary.service.impl;

import com.trunk.core.base.BaseServiceImpl;
import com.trunk.core.dictionary.condition.DictionaryCondition;
import com.trunk.core.dictionary.condition.DictionaryTypeCondition;
import com.trunk.core.dictionary.dao.DictionaryMapper;
import com.trunk.core.dictionary.dao.DictionaryTypeMapper;
import com.trunk.core.dictionary.entity.Dictionary;
import com.trunk.core.dictionary.entity.DictionaryType;
import com.trunk.core.dictionary.service.DictionaryService;
import com.trunk.core.dictionary.service.DictionaryTypeService;
import com.trunk.core.query.ConditionQuery;
import com.trunk.core.utils.ListCheckCondition;
import com.trunk.core.utils.ListUtils;
import com.trunk.core.utils.RedisUtils;
import com.trunk.core.utils.TreeOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author fanhaoming
 * @ClassName DictionaryServiceImpl
 * @Description TODO
 * @Date 2019/8/7 11:59
 * @Version
 **/
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements DictionaryService {

}
