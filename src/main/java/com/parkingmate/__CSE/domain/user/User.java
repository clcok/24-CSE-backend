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
    @Column(nullable = false)
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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // 일반 사용자 추가 정보 설정 메서드
    public void setUserInfo(String carNum, String telephone) {
        this.carNum = carNum;
        this.telephone = telephone;
    }

}

}

