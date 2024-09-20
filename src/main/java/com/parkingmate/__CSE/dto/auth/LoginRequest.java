package com.parkingmate.__CSE.dto.auth;

public record LoginRequest(
        String userName,
        String password
) {
}
