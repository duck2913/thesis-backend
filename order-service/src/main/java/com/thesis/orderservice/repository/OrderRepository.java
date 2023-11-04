package com.thesis.orderservice.repository;

import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserIdOrderByCreatedAtDesc(Integer userId);

    List<Order> findByUseDeliveryTrue();

    List<Order> findByStatusNot(Status status);
}