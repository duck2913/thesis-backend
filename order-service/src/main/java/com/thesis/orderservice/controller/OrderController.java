package com.thesis.orderservice.controller;

import com.thesis.orderservice.dto.OrderItem;
import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.entity.OrderDish;
import com.thesis.orderservice.entity.PaymentType;
import com.thesis.orderservice.entity.Status;
import com.thesis.orderservice.service.OrderDishService;
import com.thesis.orderservice.service.OrderService;
import com.thesis.orderservice.util.OrderRequest;
import com.thesis.orderservice.util.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    private final OrderDishService orderDishService;

    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        var result = getDishesItemsForDishes(allOrders);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/active")
    public ResponseEntity<List<OrderResponse>> getActiveOrders() {
        List<Order> activeOrders = orderService.getActiveOrdersForVendor();
        var result = getDishesItemsForDishes(activeOrders);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersForUser(@PathVariable Integer userId) {
        List<Order> ordersByUserId = orderService.getOrdersByUserId(userId);

        var result = getDishesItemsForDishes(ordersByUserId);
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<String> createNewOrder(@RequestBody OrderRequest request) {

        UUID orderId = UUID.randomUUID();

        var newOrder = Order.builder()
                .id(orderId)
                .userId(request.getUserId())
                .status(Status.NEW)
                .createdAt(new Date())
                .imageUrl(request.getImageUrl())
                .totalPrice(request.getTotalPrice())
                .paymentType(PaymentType.valueOf(request.getPaymentType()))
                .useDelivery(request.getUseDelivery())
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

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable UUID orderId) {
        orderService.updateOrderStatus(orderId);
        return ResponseEntity.ok("update order status successfully");
    }


    private List<OrderResponse> getDishesItemsForDishes(List<Order> orders) {
        List<OrderResponse> result = new ArrayList<OrderResponse>();

        for (Order order : orders) {
            UUID orderId = order.getId();
            List<OrderDish> orderDishes = orderDishService.getOrderItemsByOrderId(orderId);

            List<OrderItem> orderItems = orderDishes.stream()
                    .map(orderDish -> mapper.map(orderDish, OrderItem.class))
                    .toList();

            System.out.println(orderItems);

            OrderResponse orderResponse = OrderResponse.builder()
                    .id(order.getId())
                    .orderItems(orderItems)
                    .imageUrl(order.getImageUrl())
                    .totalPrice(order.getTotalPrice())
                    .status(order.getStatus())
                    .useDelivery(order.getUseDelivery())
                    .build();
            result.add(orderResponse);
        }
        return result;
    }
}