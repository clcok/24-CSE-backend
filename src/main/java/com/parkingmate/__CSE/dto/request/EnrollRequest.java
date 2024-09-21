package com.parkingmate.__CSE.dto.request;

import lombok.Getter;

@Getter
public class EnrollRequest {
    private String name;
    private String address;
    private Integer maxCar;
    private Integer price;
    private String explain;
}
