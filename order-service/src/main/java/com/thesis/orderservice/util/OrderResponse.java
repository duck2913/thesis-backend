package com.thesis.orderservice.util;

import com.thesis.orderservice.dto.OrderItem;
import com.thesis.orderservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Integer totalPrice;

    private String imageUrl;

    private List<OrderItem> orderItems;

    private Status status;
}