package com.thesis.notificationservice;

import java.util.UUID;

enum Status {
    NEW,
    COOKING,
    DELIVERY,
    DONE
}

public record OrderDto(UUID id, Integer userId, Integer shipperId, Status status) {
}