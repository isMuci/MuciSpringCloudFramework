package com.muci.framework.auth.domain.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time) {
        set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    public long remove(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    public long incr(String key, long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    public double incr(String key, double num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    public long decr(String key, long num) {
        return redisTemplate.opsForValue().decrement(key, num);
    }

    public void hSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

}
