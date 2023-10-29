package com.thesis.orderservice.service;

import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createNewOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
}