package com.trunk.core.base;

import com.trunk.core.query.ConditionQuery;

import java.util.List;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public interface BaseMapper<T> {

    /**
     * 添加记录不返回主键
     * @param entity
     * @return
     */
    int insert(T entity) ;

    /**
     * 批量插入数据
     * @param entities
     * @return
     */
    int insertBatch(List<T> entities);
    /**
     * 查询总记录数
     * @param conditionQuery
     * @return
     */
    @SuppressWarnings("rawtypes")
    int findCountByParams(ConditionQuery conditionQuery);
    /**
     * 查询记录 通过id
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 分页查询记录
     * @param conditionQuery
     * @return
     */
    List<T> listByCondition(ConditionQuery conditionQuery);

    /**
     * 更新记录
     * @param entity
     * @return
     */
    int updateById(T entity);

    /**
     * 删除记录
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(String[] ids);
}
