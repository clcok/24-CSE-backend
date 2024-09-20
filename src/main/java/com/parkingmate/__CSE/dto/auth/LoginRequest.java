package com.parkingmate.__CSE.dto.auth;

public record LoginRequest(
        String username,
        String password
) {
}
