package com.thesis.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

enum Status {
    NEW,
    COOKING,
    DELIVERY,
    DONE
}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dish_id")
    private Integer dishId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private Date createAt;

    private Integer quantity;

    private String imageUrl;
}