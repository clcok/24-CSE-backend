package com.parkingmate.__CSE.dto.auth;

import com.parkingmate.__CSE.domain.user.User;

public record UserResponse(
        Long id,
        String username,
        String telephone
) {
    // User 엔티티로부터 DTO를 생성하는 팩토리 메서드
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getTelephone()
        );
    }
}

