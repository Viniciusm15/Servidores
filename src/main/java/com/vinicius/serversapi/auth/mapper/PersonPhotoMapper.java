package com.vinicius.serversapi.auth.mapper;

import com.vinicius.serversapi.auth.dto.personPhoto.PersonPhotoRequestDto;
import com.vinicius.serversapi.auth.dto.personPhoto.PersonPhotoResponseDto;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.PersonPhoto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonPhotoMapper {

    public PersonPhoto toEntity(PersonPhotoRequestDto dto, Person person, String hash, String bucket) {
        return PersonPhoto.builder()
                .person(person)
                .date(dto.getDate() != null ? dto.getDate() : LocalDate.now())
                .hash(hash)
                .bucket(bucket)
                .build();
    }

    public PersonPhotoResponseDto toDto(PersonPhoto entity, String photoUrl) {
        PersonPhotoResponseDto dto = new PersonPhotoResponseDto();
        dto.setId(entity.getId());
        dto.setPersonId(entity.getPerson().getId());
        dto.setDate(entity.getDate());
        dto.setBucket(entity.getBucket());
        dto.setHash(entity.getHash());
        dto.setPhotoUrl(photoUrl);
        return dto;
    }

    public PersonPhotoResponseDto toDto(PersonPhoto entity) {
        return toDto(entity, null);
    }
}
