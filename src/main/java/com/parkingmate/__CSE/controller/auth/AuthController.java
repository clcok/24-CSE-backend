package com.parkingmate.__CSE.controller.auth;

import com.parkingmate.__CSE.domain.user.User;
import com.parkingmate.__CSE.dto.auth.LoginRequest;
import com.parkingmate.__CSE.dto.auth.RegisterRequest;
import com.parkingmate.__CSE.dto.auth.UserResponse;
import com.parkingmate.__CSE.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    //회원가입
    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        User user = userService.register(request);
        return UserResponse.from(user);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request, HttpSession session){
        User user = userService.login(request.username(), request.password());
        session.setAttribute("user", user);
        return UserResponse.from(user);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }




}
