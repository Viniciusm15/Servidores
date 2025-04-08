package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.person.PersonRequestDto;
import com.vinicius.serversapi.auth.dto.person.PersonResponseDto;
import com.vinicius.serversapi.auth.model.core.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequestDto dto) {
        return Person.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .motherName(dto.getMotherName())
                .fatherName(dto.getFatherName())
                .build();
    }

    public PersonResponseDto toDto(Person entity) {
        PersonResponseDto dto = new PersonResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        dto.setMotherName(entity.getMotherName());
        dto.setFatherName(entity.getFatherName());
        return dto;
    }

    public void updateFromDto(PersonRequestDto dto, Person entity) {
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setMotherName(dto.getMotherName());
        entity.setFatherName(dto.getFatherName());
    }
}
