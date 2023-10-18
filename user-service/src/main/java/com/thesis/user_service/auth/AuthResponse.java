package com.thesis.user_service.auth;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {
}