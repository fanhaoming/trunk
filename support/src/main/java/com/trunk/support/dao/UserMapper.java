package com.trunk.support.dao;

import com.trunk.core.query.ConditionQuery;
import com.trunk.support.entity.User;
import com.trunk.core.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/7/5.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
