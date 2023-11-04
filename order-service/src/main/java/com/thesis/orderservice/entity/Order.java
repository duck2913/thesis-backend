package com.thesis.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "shipper_id")
    private Integer shipperId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "created_at")
    private Date createdAt;

    private String imageUrl;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "use_delivery")
    private Boolean useDelivery;
}