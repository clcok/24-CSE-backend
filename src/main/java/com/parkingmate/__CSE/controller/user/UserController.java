package com.parkingmate.__CSE.controller.user;

import com.parkingmate.__CSE.dto.auth.LoginRequest;
import com.parkingmate.__CSE.dto.auth.RegisterRequest;
import com.parkingmate.__CSE.domain.user.User;
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

    //회원가입
    @PostMapping("/register")
    public UserResponse register(@ModelAttribute RegisterRequest request){
        userService.register(request);
        return;
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
