package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.person.PersonRequestDto;
import com.vinicius.serversapi.auth.dto.person.PersonResponseDto;
import com.vinicius.serversapi.auth.model.core.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonService {
    Person createAndReturnEntity(PersonRequestDto dto);
    PersonResponseDto getById(Long id);
    Page<PersonResponseDto> getAll(Pageable pageable);
    PersonResponseDto update(Long id, PersonRequestDto dto);
    void delete(Long id);
}
