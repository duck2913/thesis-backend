package com.thesis.notificationservice.dto;

import java.util.UUID;

public record EventDto(UUID orderId, Integer userId, String message) {
}