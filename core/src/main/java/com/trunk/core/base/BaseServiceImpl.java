package com.trunk.core.base;

import com.trunk.core.query.ConditionQuery;
import com.trunk.core.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{

    @Autowired
    BaseMapper<T> baseMapper;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public T insert(T entity) {
        baseMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertBatch(List<T> entities) {
        return baseMapper.insertBatch(entities);
    }

    @Override
    public int findCountByParams(ConditionQuery conditionQuery) {
        return baseMapper.findCountByParams(conditionQuery);
    }

    @Override
    public T findById(String id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<T> listByCondition(AbstractCondition condition) {
        ConditionQuery conditionQuery = new ConditionQuery(condition);
        return baseMapper.listByCondition(conditionQuery);
    }

    @Override
    public int update(T entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public int delete(String id) {
        return baseMapper.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return baseMapper.deleteBatch(ids);
    }
}
