package com.thesis.orderservice.client;

import com.thesis.orderservice.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserServiceClient {
    @GetExchange("/users/{userId}")
    public User getUserInfo(@PathVariable Integer userId);
}