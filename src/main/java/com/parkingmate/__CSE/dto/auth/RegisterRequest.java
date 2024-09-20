package com.parkingmate.__CSE.dto.auth;

import com.parkingmate.__CSE.domain.user.User;

public record RegisterRequest(
        String username,
        String password,
        String telephone
) {
    public User createUser(){
        return new User(this.username(),this.password(),this.telephone());
    }
}
