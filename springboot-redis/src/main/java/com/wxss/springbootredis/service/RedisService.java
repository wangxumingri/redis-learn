package com.wxss.springbootredis.service;

import org.springframework.data.redis.core.*;

import java.util.Map;

/**
 * 封装RedisTemplate操作
 */
public interface RedisService {
    // =========Operation=========

    HashOperations<String, String, Object> hashOperations();
    ValueOperations<String, Object> valueOperations();
    ListOperations<String, Object> listOperations();
    SetOperations<String, Object> setOperations();
    ZSetOperations<String, Object> zSetOperations();

    // =========General=========

    boolean expire(String key,long time);
    long getExpire(String key);
    boolean hasKey(String key);
    void del(String ... key);

    // =========String=========

    Object get(String key);
    boolean set(String key,Object value);
    boolean set(String key,Object value,long time);
    long incr(String key, long delta);
    long decr(String key, long delta);

    // =========Map=========

    Object hget(String key,String item);
    Map<Object,Object> hmget(String key);
    boolean hmset(String key, Map<String,Object> map);
    boolean hmset(String key, Map<String,Object> map, long time);
    boolean hset(String key,String item,Object value);
    boolean hset(String key,String item,Object value,long time);
    void hdel(String key, Object... item);
    boolean hHasKey(String key, String item);
    double hincr(String key, String item,double by);
    double hdecr(String key, String item,double by);
}
