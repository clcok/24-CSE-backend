package com.parkingmate.__CSE.domain;

import com.parkingmate.__CSE.dto.request.EnrollRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false, name="user_id")
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String name; //주차장 이름

    @Column(nullable = false)
    private String address; //주차장 주소

    @Column(nullable = false)
    private Integer maxCar; //최대 주차 개수

    @Column(nullable = false)
    private Integer useCar;

    @Column(nullable = false)
    private Integer price; //가격

    @Column
    private String explain; //주차장 설명

    @Column
    private Boolean isAvailable; //주차 가능 여부

    @Column
    private String startTime; //예약 시작 시간

    @Column
    private String endTime; //예약 끝 시간

    public ParkingSpace(User user, EnrollRequest enrollRequest){
        this.user = user;
        this.name = enrollRequest.getName();
        this.address = enrollRequest.getAddress();
        this.maxCar = enrollRequest.getMaxCar();
        this.price = enrollRequest.getPrice();
        this.explain = enrollRequest.getExplain();
        this.useCar = 0;
        this.isAvailable = true;
    }

}
