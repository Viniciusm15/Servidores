package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.address.AddressRequestDto;
import com.vinicius.serversapi.auth.dto.address.AddressResponseDto;
import com.vinicius.serversapi.auth.model.core.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressRequestDto dto) {
        Address address = new Address();
        address.setStreetType(dto.getStreetType());
        address.setStreetName(dto.getStreetName());
        address.setNumber(dto.getNumber());
        address.setNeighborhood(dto.getNeighborhood());
        return address;
    }

    public AddressResponseDto toDto(Address entity) {
        AddressResponseDto dto = new AddressResponseDto();
        dto.setId(entity.getId());
        dto.setStreetType(entity.getStreetType());
        dto.setStreetName(entity.getStreetName());
        dto.setNumber(entity.getNumber());
        dto.setNeighborhood(entity.getNeighborhood());

        if (entity.getCity() != null) {
            dto.setCityId(entity.getCity().getId());
            dto.setCityName(entity.getCity().getName());
            dto.setState(entity.getCity().getState());
        }

        return dto;
    }

    public void updateFromDto(AddressRequestDto dto, Address entity) {
        entity.setStreetType(dto.getStreetType());
        entity.setStreetName(dto.getStreetName());
        entity.setNumber(dto.getNumber());
        entity.setNeighborhood(dto.getNeighborhood());
    }
}
