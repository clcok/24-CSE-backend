package com.parkingmate.__CSE.dto.response;

import lombok.Getter;

@Getter
public class AvailableSpaceResponse {
    private String ownerName;
    private String parkingName;
    private Integer maxCar;
    private Integer useCar;
    private Integer price;
    private String explain;
    private Boolean isAvailable;

    public AvailableSpaceResponse(String ownerName, String parkingName, Integer maxCar, Integer useCar, Integer price, String explain, Boolean isAvailable){
        this.ownerName = ownerName;
        this.parkingName = parkingName;
        this.maxCar = maxCar;
        this.useCar = useCar;
        this.price = price;
        this.explain = explain;
        this.isAvailable = isAvailable;
    }
}
