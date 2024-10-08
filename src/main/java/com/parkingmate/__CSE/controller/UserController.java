package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.MoneyChargeRequest;
import com.parkingmate.__CSE.dto.request.UserCarRequest;
import com.parkingmate.__CSE.dto.response.MyPageResponse;
import com.parkingmate.__CSE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 현재 사용자 정보 조회
    @GetMapping("/mypage")
    public MyPageResponse getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        return userService.showMyPage(user);
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/car/exist")
    public Boolean carExist(HttpSession session){
        return userService.carExist(session);
    }

    @PostMapping("/car/enroll")
    public void carEnroll(@RequestBody UserCarRequest usercarRequest, HttpSession session){
        userService.carEnroll(usercarRequest, session);
    }

    @PostMapping("/moneycharge")
    public void moneyCharge(@RequestBody MoneyChargeRequest moneyChargeRequest, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        userService.moneyCharge(moneyChargeRequest,user);
    }
}