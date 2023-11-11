package com.thesis.menu_service.config;

import com.thesis.menu_service.entity.Dish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, List<Dish>> redisTemplate() {
        RedisTemplate<String, List<Dish>> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(jsonSerializer);
        template.setValueSerializer(jsonSerializer);
        template.setHashKeySerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        return template;
    }
}