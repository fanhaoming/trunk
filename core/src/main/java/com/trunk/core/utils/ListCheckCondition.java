package com.trunk.core.utils;

/**
 * @author fanhaoming
 * @ClassName ListCheckCondition
 * @Description TODO
 * @Date 2019/8/7 16:39
 * @Version
 **/
public interface ListCheckCondition<T> {
    Boolean check(T t);
}
