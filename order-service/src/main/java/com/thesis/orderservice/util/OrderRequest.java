package com.thesis.orderservice.util;

import com.thesis.orderservice.dto.DishDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer userId;

    private List<DishDto> dishes;

    private String imageUrl;
    
}