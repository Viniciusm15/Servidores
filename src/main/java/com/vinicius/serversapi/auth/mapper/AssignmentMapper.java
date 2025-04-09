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
                .assignmentDate(dto.getAssignmentDate())
                .removalDate(dto.getRemovalDate())
                .ordinance(dto.getOrdinanceNumber())
                .build();
    }

    public AssignmentResponseDto toDto(Assignment entity) {
        AssignmentResponseDto dto = new AssignmentResponseDto();
        dto.setId(entity.getId());
        dto.setPersonId(entity.getPerson().getId());
        dto.setPersonName(entity.getPerson().getName());
        dto.setUnitName(entity.getUnit().getName());
        dto.setRegistrationNumber(entity.getPerson().getPermanentEmployee() != null
                ? entity.getPerson().getPermanentEmployee().getRegistrationNumber()
                : null);
        return dto;
    }
}
