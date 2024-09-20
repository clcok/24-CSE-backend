package com.parkingmate.__CSE.dto;

public record OwnerAdditioinalInfo(
        String telephone,
        String carNum,
        String parkingName,
        String information,
        String address,
        boolean isAvaliable,
        Integer maxParking,
        Integer PricePer30Min
) {

}
