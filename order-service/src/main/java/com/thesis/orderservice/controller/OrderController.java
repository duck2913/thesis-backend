package com.thesis.orderservice.controller;

import com.thesis.orderservice.repository.OrderRepository;
import com.thesis.orderservice.util.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<String> getAllOrders() {
        return ResponseEntity.ok("get orders successfully");
    }


    @PostMapping
    public ResponseEntity<String> createNewOrder(@RequestBody OrderRequest request) {
        System.out.println(request);
        return ResponseEntity.ok("create order successfully");
    }

}