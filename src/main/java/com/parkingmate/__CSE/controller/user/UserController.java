package com.parkingmate.__CSE.controller.user;

import com.parkingmate.__CSE.dto.auth.LoginRequest;
import com.parkingmate.__CSE.dto.auth.RegisterRequest;
import com.parkingmate.__CSE.domain.user.User;
import com.parkingmate.__CSE.dto.auth.UserResponse;
import com.parkingmate.__CSE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 현재 사용자 정보 조회
    @GetMapping("/me")
    public void getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        //return new UserResponse(user);
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "home";
    }

}
