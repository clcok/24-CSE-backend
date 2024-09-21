package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.dto.auth.RegisterRequest;

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

    public User register(RegisterRequest request) {

        if(userRepository.findByUserNameOrName(request.userName(), request.name()).isPresent()){
            throw new RuntimeException("이미 존재하는 사용자이거나 닉네임이 중복됩니다.");
        }

        User user = request.createUser();
        return userRepository.save(user);
    }

    public User login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다."));
    }

    public Boolean carExist(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user.getCar() == null)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    public void carEnroll(UserCarRequest usercarRequest, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setCar(usercarRequest.getCar());
        userRepository.save(user); //setter안쓰고 하는 방법은 없을까.
    }

}
