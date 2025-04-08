package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.assignment.AssignmentRequestDto;
import com.vinicius.serversapi.auth.dto.assignment.AssignmentResponseDto;
import com.vinicius.serversapi.auth.model.core.Assignment;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.Unit;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public Assignment toEntity(AssignmentRequestDto dto, Person person, Unit unit) {
        return Assignment.builder()
                .person(person)
                .unit(unit)
                .build();
    }

    public AssignmentResponseDto toDto(Assignment entity) {
        AssignmentResponseDto dto = new AssignmentResponseDto();
        dto.setId(entity.getId());
        dto.setPersonName(entity.getPerson().getName());
        dto.setUnitName(entity.getUnit().getName());
        return dto;
    }
}
