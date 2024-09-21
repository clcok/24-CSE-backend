package com.parkingmate.__CSE.domain;

import com.parkingmate.__CSE.dto.request.EnrollRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "parkingSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>();

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

    // 관계 설정 메서드
    public void assignUser(User user) {
        this.user = user;
        user.getParkingSpaceList().add(this);
    }

    public void addUseCar(){
        if(useCar >= maxCar) {
            throw new RuntimeException("주차 공간이 부족합니다.");
        }
        if(useCar == maxCar - 1){
            isAvailable = false;
        }
        this.useCar++;
    }
}
