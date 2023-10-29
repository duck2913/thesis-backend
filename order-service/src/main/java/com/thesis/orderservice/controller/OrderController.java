package com.thesis.orderservice.controller;

import com.thesis.orderservice.dto.OrderItem;
import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.entity.OrderDish;
import com.thesis.orderservice.entity.Status;
import com.thesis.orderservice.service.OrderDishService;
import com.thesis.orderservice.service.OrderService;
import com.thesis.orderservice.util.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    private final OrderDishService orderDishService;

    @GetMapping
    public ResponseEntity<String> getAllOrders() {
        return ResponseEntity.ok("get orders successfully");
    }


    @PostMapping
    public ResponseEntity<String> createNewOrder(@RequestBody OrderRequest request) {

        UUID orderId = UUID.randomUUID();

        var newOrder = Order.builder()
                .id(orderId)
                .userId(request.getUserId())
                .status(Status.NEW)
                .createAt(new Date())
                .imageUrl(request.getImageUrl())
                .totalPrice(request.getTotalPrice())
                .build();

        for (OrderItem orderItem : request.getOrderItems()) {
            OrderDish orderDish = OrderDish.builder()
                    .orderId(newOrder.getId())
                    .dishName(orderItem.getDishName())
                    .quantity(orderItem.getQuantity())
                    .build();

            orderDishService.createNewOrderDish(orderDish);
        }

        orderService.createNewOrder(newOrder);

        return ResponseEntity.ok("create order successfully");
    }

}