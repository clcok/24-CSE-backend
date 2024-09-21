package com.parkingmate.__CSE.dto.response;

import com.parkingmate.__CSE.domain.User;

public record MyPageResponse(
        String userName,
        String name,
        String telephone,
        String carNum
) {
}
