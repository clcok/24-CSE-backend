package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.service.ParkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

    @Autowired
    public ParkService parkService;

    @PostMapping("/enroll/parkingspace")
    public void parkingEnroll(@RequestBody EnrollRequest enrollRequest, HttpSession session){
        parkService.enrollParkingSpace(enrollRequest, session);
    }
}
