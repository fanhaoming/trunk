package com.trunk.core.utils;

import java.util.List;

/**
 * @author fanhaoming
 * @ClassName TreeCheck
 * @Description TODO
 * @Date 2019/8/8 10:04
 * @Version
 **/
public interface TreeOperation<T>{

    Boolean check(T t1,T t2);

    void put(T t,List<T> list);
}
