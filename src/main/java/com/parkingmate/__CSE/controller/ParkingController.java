package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.Reservation;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.dto.request.ReservationRequest;
import com.parkingmate.__CSE.service.ParkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    public ParkService parkService;

    @PostMapping("/enroll")
    public void parkingEnroll(@RequestBody EnrollRequest enrollRequest, HttpSession session){
        parkService.enrollParkingSpace(enrollRequest, session);
    }


}
