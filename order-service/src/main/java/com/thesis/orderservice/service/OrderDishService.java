package com.thesis.orderservice.service;

import com.thesis.orderservice.entity.OrderDish;
import com.thesis.orderservice.repository.OrderDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDishService {
    @Autowired
    OrderDishRepository orderDishRepository;

    public void createNewOrderDish(OrderDish newOrderDish) {
        orderDishRepository.save(newOrderDish);
    }

    public List<OrderDish> getOrderItemsByOrderId(UUID orderId) {
        return orderDishRepository.findByOrderId(orderId);
    }
}