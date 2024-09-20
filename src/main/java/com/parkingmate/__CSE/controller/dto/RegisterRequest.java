package com.parkingmate.__CSE.controller.dto;

import com.parkingmate.__CSE.domain.user.User;

public record RegisterRequest(
        String username,
        String password
) {
    public User createUser(){
        return new User(this.username(),this.password());
    }
}
