package com.parkingmate.__CSE.domain;

import com.parkingmate.__CSE.dto.request.EnrollRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, length = 13)
    private String telephone;

    @Column(unique = true)
    private String car; //차량 번호

    @Column
    private Integer chargeAmount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private List<ParkingSpace> parkingSpaceList = new ArrayList<>();

    //User는 여러 개의 Reservation을 가질 수 있으며 변경관계가 생기면 자동로드 및 user관계 끊어진 reservation은 삭제.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private List<Reservation> reservationList = new ArrayList<>();

    public User(String userName, String password, String name, String telephone){
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.chargeAmount = 0;
    }


}
