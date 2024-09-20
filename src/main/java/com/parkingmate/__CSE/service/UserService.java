package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.UserCarRequest;
import com.parkingmate.__CSE.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void carEnroll(UserCarRequest usercarRequest, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setCar(usercarRequest.getCar());
        userRepository.save(user);
    }

}
