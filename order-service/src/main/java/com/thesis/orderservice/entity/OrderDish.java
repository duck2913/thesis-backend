package com.thesis.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "orders_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "BINARY(16)")
    private UUID orderId;

    private String dishName;

    private Integer quantity;
}