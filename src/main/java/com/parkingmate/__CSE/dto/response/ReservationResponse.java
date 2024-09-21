package com.parkingmate.__CSE.dto.response;

import com.parkingmate.__CSE.domain.Reservation;

public record ReservationResponse(
        Long id,
        Long userId,
        Long parkingSpaceId,
        String date,
        String startTime,
        String endTime
){
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getParkingSpace().getId(),
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }

}
