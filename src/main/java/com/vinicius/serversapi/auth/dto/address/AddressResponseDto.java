package com.vinicius.serversapi.auth.dto.address;

import lombok.Data;

@Data
public class AddressResponseDto {
    private Long id;
    private String streetType;
    private String streetName;
    private String number;
    private String neighborhood;
    private Long cityId;
    private String cityName;
    private String state;
}
