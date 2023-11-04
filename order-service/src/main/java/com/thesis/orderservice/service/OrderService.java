package com.thesis.orderservice.service;

import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.entity.Status;
import com.thesis.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createNewOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }

    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Order> getAllOrdersForShipper() {
        return orderRepository.findByUseDeliveryTrue();
    }

    public List<Order> getActiveOrdersForVendor() {
        return orderRepository.findByStatusNot(Status.DONE);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}