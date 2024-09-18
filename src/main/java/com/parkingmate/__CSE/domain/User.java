package com.parkingmate.__CSE.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName; //아이디

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false, unique = true)
    private String name; //닉네임

    @Column(nullable = false, unique = true)
    private String email; //이메일

    @Column(nullable = false, unique = true)
    private String car; //차량 번호

    User(String userName, String password, String name, String email, String car){
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.car = car;
    }
}
