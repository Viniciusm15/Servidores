package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.person.PersonRequestDto;
import com.vinicius.serversapi.auth.dto.person.PersonResponseDto;
import com.vinicius.serversapi.auth.mapper.PersonMapper;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.service.contract.IPersonService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public Person createAndReturnEntity(PersonRequestDto dto) {
        Person person = personMapper.toEntity(dto);
        return personRepository.save(person);
    }

    public PersonResponseDto getById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        return personMapper.toDto(person);
    }

    public Page<PersonResponseDto> getAll(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(personMapper::toDto);
    }

    public PersonResponseDto update(Long id, PersonRequestDto dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        personMapper.updateFromDto(dto, person);
        Person updated = personRepository.save(person);
        return personMapper.toDto(updated);
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        person.setDeleted(true);
        personRepository.save(person);
    }
}
