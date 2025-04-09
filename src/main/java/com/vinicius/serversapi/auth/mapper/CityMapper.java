package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.city.CityRequestDto;
import com.vinicius.serversapi.auth.dto.city.CityResponseDto;
import com.vinicius.serversapi.auth.model.core.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public City toEntity(CityRequestDto dto) {
        City city = new City();
        city.setName(dto.getName());
        city.setState(dto.getState());
        return city;
    }

    public CityResponseDto toDto(City city) {
        CityResponseDto dto = new CityResponseDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setState(city.getState());
        return dto;
    }

    public void updateFromDto(CityRequestDto dto, City city) {
        city.setName(dto.getName());
        city.setState(dto.getState());
    }
}
