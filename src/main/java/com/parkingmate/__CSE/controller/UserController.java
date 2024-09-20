package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.dto.request.UserCarRequest;
import com.parkingmate.__CSE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/car/enroll")
    public void carEnroll(@RequestBody UserCarRequest usercarRequest, HttpSession session){
        userService.carEnroll(usercarRequest, session);
    }
}