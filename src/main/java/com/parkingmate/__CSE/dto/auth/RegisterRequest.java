package com.parkingmate.__CSE.dto.auth;

import com.parkingmate.__CSE.domain.User;

public record RegisterRequest(
        String userName,
        String password,
        String telephone,
        String name
) {
    public User createUser(){
        return new User(
                this.userName(),
                this.password(),
                this.name(),
                this.telephone());
    }
}
