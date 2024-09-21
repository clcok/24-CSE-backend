package com.parkingmate.__CSE.dto.request;

public record ReservationRequest(

        String date,
        String startTime,
        String endTime
){}
