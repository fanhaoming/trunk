package com.trunk.core.web;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author fanhaoming
 * @ClassName ResponseResult
 * @Description TODO
 * @Date 2019/8/1 15:04
 * @Version
 **/
@Setter
@Getter
public class ResponseResult {
    private boolean status;
    private String code;
    private HashMap<String, Object> data = new HashMap<>();
    private String msg;

    public void addResult(String key,Object value) {
        this.data.put(key,value);
    }
    public void addResult(ISetResponseData iSetData) {
        HashMap<String,Object> data = new HashMap<>();
        iSetData.action(data);
        this.data.putAll(data);
    }

    public static ResponseResult ok() {
        ResponseResult result = new ResponseResult();
        result.status = true;
        HashMap<String, Object> data = new HashMap<String, Object>();
        result.data = data;
        result.code = "200";
        return result;
    }

    public static ResponseResult ok(String key, Object value) {
        ResponseResult result = new ResponseResult();
        result.status = true;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(key, value);
        result.code = "200";
        result.data = data;
        return result;
    }


    public static ResponseResult ok(ISetResponseData iSetData) {
        ResponseResult result = new ResponseResult();
        result.status = true;
        HashMap<String, Object> data = new HashMap<String, Object>();
        iSetData.action(data);
        result.code = "200";
        result.data = data;
        return result;
    }
    public static ResponseResult error() {
        ResponseResult result = new ResponseResult();
        result.status = false;
        result.code = "500";
        return result;
    }

}
