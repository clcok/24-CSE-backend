package com.parkingmate.__CSE.dto.auth;

import com.parkingmate.__CSE.domain.User;

public record UserResponse(
        Long id,
        String userName,
        String telephone
) {

    // User 엔티티로부터 DTO를 생성하는 팩토리 메서드
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getTelephone()
        );
    }
}

