package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.unit.UnitRequestDto;
import com.vinicius.serversapi.auth.dto.unit.UnitResponseDto;
import com.vinicius.serversapi.auth.model.core.*;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitRequestDto dto) {
        return Unit.builder()
                .name(dto.getName())
                .acronym(dto.getAcronym())
                .build();
    }

    public UnitResponseDto toDto(Unit entity) {
        UnitResponseDto dto = new UnitResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAcronym(entity.getAcronym());
        return dto;
    }
}
