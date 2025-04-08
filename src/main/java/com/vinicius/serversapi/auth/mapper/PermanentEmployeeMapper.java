package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeResponseDto;
import com.vinicius.serversapi.auth.model.core.PermanentEmployee;
import com.vinicius.serversapi.auth.model.core.Person;
import org.springframework.stereotype.Component;

@Component
public class PermanentEmployeeMapper {

    public PermanentEmployee toEntity(PermanentEmployeeRequestDto dto, Person person) {
        return PermanentEmployee.builder()
                .id(person.getId())
                .registrationNumber(dto.getRegistrationNumber())
                .person(person)
                .build();
    }

    public PermanentEmployeeResponseDto toDto(PermanentEmployee entity) {
        PermanentEmployeeResponseDto dto = new PermanentEmployeeResponseDto();
        dto.setId(entity.getId());
        dto.setRegistrationNumber(entity.getRegistrationNumber());
        dto.setPersonId(entity.getPerson().getId());
        dto.setPersonName(entity.getPerson().getName());
        return dto;
    }
}
