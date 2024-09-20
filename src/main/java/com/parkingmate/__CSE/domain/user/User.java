package com.parkingmate.__CSE.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //공통 필드
    @Column(nullable = false, unique = true) //id중복방지용 unique
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 13)
    private String telephone;

    private String carNum;

    // 사장님 필드
    private String parkingName; //주차장이름
    private String address; //주차장 주소
    private String information; //주차장 정보
    private Integer maxParking; //최대 주차 대수
    private Integer PricePer30Min;
    private boolean isAvaliable; //차량 지금 가능한지

    public User(String username, String password, String telephone) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }


}


