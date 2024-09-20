package com.parkingmate.__CSE.controller.dto;

import com.parkingmate.__CSE.domain.user.User;
import com.parkingmate.__CSE.domain.user.UserRole;

public record UserResponse(
        Long id,
        String username,
        UserRole role,
        String carNumber,
        String parkingLotAddress,
        Integer maxParkingSpots,
        Integer parkingPricePer30Min
) {
    // User 엔티티로부터 DTO를 생성하는 팩토리 메서드
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getTelephone(),
                user.getRole(),
                user.getCarNum(),
                user.getAddress(),
                user.getParkingName(),
                user.getPricePer30Min(),
                user.getMaxParking(),
        );
    }
}

