package com.trunk.core.utils;

import java.util.*;

/**
 * @author fanhaoming
 * @ClassName ListUtils
 * @Description TODO
 * @Date 2019/8/7 16:39
 * @Version
 **/
public class ListUtils {

    public static <T> List<T> findAll(List<T> list, ListCheckCondition<T> listCheckCondition){
        if(list == null || list.size() <= 0){
            return Collections.emptyList();
        }
        List<T> results = new ArrayList<T>();
        list.forEach(t->{
            if(listCheckCondition.check(t)){
                results.add(t);
            }
        });
        return results;
    }



    public static <T> List<T> loadTreeByRecursion(T t, List<T> list, TreeOperation<T> operation){
        List<T> childList = new ArrayList<T>();
        List<T> tempList = new ArrayList<T>(list);
        list.forEach(data->{
            if(operation.check(t,data)) {
                tempList.remove(data);
                operation.put(data,loadTreeByRecursion(data,tempList,operation));
                childList.add(data);
            }
        });
        return childList;
    }




}
