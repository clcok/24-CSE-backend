package com.parkingmate.__CSE.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false) //User가 존재해야 Resevation 존재
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(nullable = false) //ParkingSpace가 존재해야 Resevation 존재
    @ManyToOne(fetch = FetchType.LAZY)
    private ParkingSpace parkingSpace;

    @Column(nullable = false)
    private String date; //날짜

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    public Reservation(User user, ParkingSpace parkingSpace, String data, String startTime, String endTime) {
        this.user = user;
        this.parkingSpace = parkingSpace;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
