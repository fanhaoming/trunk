package com.trunk.core.utils;

import org.apache.commons.lang3.*;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class RedisUtils {
    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    public boolean set(String key, Object value) {
        try {
            if (StringUtils.isNotEmpty(key) && null != value) {
                redisTemplate.opsForValue().set(key, value);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object hget(String name,Object key){
        return name == null ? null:redisTemplate.opsForHash().get(name,key);
    }

    public boolean hset(String  name,Object key,Object value){
        if (StringUtils.isNotEmpty(name) && null != key && null != value) {
            redisTemplate.opsForHash().put(name,key,value);
            return true;
        }
        return false;
    }
}