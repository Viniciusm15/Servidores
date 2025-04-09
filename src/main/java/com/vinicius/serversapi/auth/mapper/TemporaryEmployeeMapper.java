package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.employee.TemporaryEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.TemporaryEmployeeResponseDto;
import com.vinicius.serversapi.auth.model.core.*;
import org.springframework.stereotype.Component;

@Component
public class TemporaryEmployeeMapper {

    public TemporaryEmployee toEntity(TemporaryEmployeeRequestDto dto, Person person) {
        return TemporaryEmployee.builder()
                .admissionDate(dto.getAdmissionDate())
                .dismissalDate(dto.getDismissalDate())
                .person(person)
                .build();
    }

    public TemporaryEmployeeResponseDto toDto(TemporaryEmployee entity) {
        TemporaryEmployeeResponseDto dto = new TemporaryEmployeeResponseDto();
        dto.setId(entity.getId());
        dto.setAdmissionDate(entity.getAdmissionDate());
        dto.setDismissalDate(entity.getDismissalDate());
        dto.setPersonName(entity.getPerson().getName());
        return dto;
    }
}
