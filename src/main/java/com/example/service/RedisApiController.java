package com.example.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class RedisApiController implements RedisApi {

    @Autowired
    StringRedisTemplate redisTemplate;

    private final static Map<String, String> SUCCESS = Collections.singletonMap("message", "success");

    @Override
    public Map<String, String> addSimple(@PathVariable(value = "key") String key,
        @PathVariable(value = "stale") Integer stale, @RequestBody String value) {
        if (stale == null) {
            redisTemplate.opsForValue().set(key, value);
        } else {
            redisTemplate.opsForValue().set(key, value, stale, TimeUnit.SECONDS);
        }
        return SUCCESS;
    }

    @Override
    public Map<String, String> addSimple(@PathVariable(value = "key") String key, @RequestBody String value) {
        return addSimple(key, null, value);
    }

    @Override
    public Map<String, String> getSimple(@PathVariable(value = "key") String key) {
        String value = redisTemplate.opsForValue().get(key);
        return Collections.singletonMap("value", value);
    }

    @Override
    public Map<String, String> addToList(@PathVariable(value = "key") String key, @RequestBody List<String> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        return SUCCESS;
    }

    @Override
    public Map<String, List<String>> getListRange(@PathVariable(value = "key") String key,
        @RequestParam(name = "start") Long start, @RequestParam(name = "end") Long end) {
        List<String> values = redisTemplate.opsForList().range(key, start, end);
        return Collections.singletonMap("values", values);
    }

    @Override
    public Map<String, String> popListValue(@PathVariable(value = "key") String key) {
        String value = redisTemplate.opsForList().leftPop(key);
        return Collections.singletonMap("value", value);
    }

    @Override
    public Map<String, String> addHashValues(@PathVariable(value = "key") String key, @RequestBody Map<String, String> values) {
        redisTemplate.opsForHash().putAll(key, values);
        return SUCCESS;
    }

    @Override
    public Map<Object, Object> getHash(@PathVariable(value = "key") String key) {
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        return Collections.singletonMap("values", values);
    }

    @Override
    public Map<String, String> getHashValue(@PathVariable(value = "key") String key, @PathVariable(value = "name") String name) {
        String value = (String) redisTemplate.opsForHash().get(key, name);
        return Collections.singletonMap(name, value);
    }

}
