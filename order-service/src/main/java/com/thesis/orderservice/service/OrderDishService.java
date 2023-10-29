package com.thesis.orderservice.service;

import com.thesis.orderservice.entity.OrderDish;
import com.thesis.orderservice.repository.OrderDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDishService {
    @Autowired
    OrderDishRepository orderDishRepository;

    public void createNewOrderDish(OrderDish newOrderDish) {
        orderDishRepository.save(newOrderDish);
    }
}