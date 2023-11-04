package com.thesis.orderservice.repository;

import com.thesis.orderservice.entity.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
    List<OrderDish> findByOrderId(UUID orderId);
}