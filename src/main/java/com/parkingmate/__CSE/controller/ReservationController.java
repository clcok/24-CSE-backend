package com.parkingmate.__CSE.controller;

import com.parkingmate.__CSE.domain.Reservation;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.ReservationRequest;
import com.parkingmate.__CSE.dto.response.ReservationResponse;
import com.parkingmate.__CSE.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create/{parkingSpaceId}")
    public ReservationResponse createReservation(
            @PathVariable Long parkingSpaceId,
            @RequestBody ReservationRequest reservationRequest,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");

        if(user == null){
            throw new RuntimeException("로그인이 되어있지 않습니다.");
        }

        Reservation reservation = reservationService.createReservation(user, parkingSpaceId, reservationRequest);
        return ReservationResponse.from(reservation);
    }
}
