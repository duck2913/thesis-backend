package com.thesis.menu_service.service;

import com.thesis.menu_service.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, List<Dish>> redisTemplate;

    public void saveData(String key, List<Dish> menu) {
        redisTemplate.opsForValue().set(key, menu);
    }

    public List<Dish> getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}