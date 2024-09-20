package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.dto.auth.RegisterRequest;
import com.parkingmate.__CSE.domain.user.User;
import com.parkingmate.__CSE.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequest request) {

        if(userRepository.findByUsername(request.username()).isPresent()){
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        User user = request.createUser();
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다."));
    }
}
