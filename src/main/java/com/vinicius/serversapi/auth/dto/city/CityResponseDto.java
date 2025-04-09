package com.vinicius.serversapi.auth.dto.city;

import lombok.Data;

@Data
public class CityResponseDto {
    private Long id;
    private String name;
    private String state;
}
