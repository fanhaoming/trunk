package com.trunk.core.base;

import com.trunk.core.query.ConditionQuery;

import java.util.List;

/**
 * Created by Administrator on 2019/7/5.
 */
public interface BaseService<T extends BaseEntity> {
    public String insert(T entity) ;

    public int insertBatch(List<T> entities);

    public int findCountByParams(ConditionQuery conditionQuery);

    public T findById(String id);


    public List<T> listByCondition(AbstractCondition condition);

    public int update(T entity);

    public int delete(String id);
    public int deleteBatch(String[] ids);
}
