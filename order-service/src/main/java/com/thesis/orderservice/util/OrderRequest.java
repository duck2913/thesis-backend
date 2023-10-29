package com.thesis.orderservice.util;

import com.thesis.orderservice.dto.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer userId;

    private List<OrderItem> orderItems;

    private String imageUrl;

    private Integer totalPrice;

}