package com.parkingmate.__CSE.controller.dto;

import com.parkingmate.__CSE.domain.user.User;

public record RegisterRequest(
        String userName,
        String password
) {
    public User createUser(){
        System.out.println(this.userName()+this.password()+"plz");
        return new User(this.userName(),this.password());
    }
}
