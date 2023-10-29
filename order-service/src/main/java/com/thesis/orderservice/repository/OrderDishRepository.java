package com.thesis.orderservice.repository;

import com.thesis.orderservice.entity.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
}