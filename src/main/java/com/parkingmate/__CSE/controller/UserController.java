package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.controller.dto.LoginRequest;
import com.parkingmate.__CSE.controller.dto.RegisterRequest;
import com.parkingmate.__CSE.domain.user.User;
import com.parkingmate.__CSE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    //회원가입
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request){
        userService.register(request);
        return "redirect:/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        System.out.println("로그인 페이지 접근");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, HttpSession session) {
        //System.out.println(request.userName()+request.password()+"login");
        User loginUser = userService.login(request.username(), request.password());
        session.setAttribute("user", loginUser);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "home";
    }

}
