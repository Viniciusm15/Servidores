package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.employee.*;
import com.vinicius.serversapi.auth.dto.employee.TemporaryEmployeeResponseDto;
import com.vinicius.serversapi.auth.mapper.TemporaryEmployeeMapper;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.TemporaryEmployee;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.repository.TemporaryEmployeeRepository;
import com.vinicius.serversapi.auth.service.contract.ITemporaryEmployeeService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemporaryEmployeeService implements ITemporaryEmployeeService {

    private final TemporaryEmployeeRepository repository;
    private final PersonRepository personRepository;
    private final TemporaryEmployeeMapper mapper;

    public TemporaryEmployeeResponseDto create(TemporaryEmployeeRequestDto dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        TemporaryEmployee employee = mapper.toEntity(dto, person);
        TemporaryEmployee saved = repository.save(employee);
        return mapper.toDto(saved);
    }

    public TemporaryEmployeeResponseDto getById(Long id) {
        TemporaryEmployee employee = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor temporário não encontrado"));
        return mapper.toDto(employee);
    }

    public Page<TemporaryEmployeeResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public TemporaryEmployeeResponseDto update(Long id, TemporaryEmployeeRequestDto dto) {
        TemporaryEmployee existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor temporário não encontrado"));

        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        existing.setAdmissionDate(dto.getAdmissionDate());
        existing.setDismissalDate(dto.getDismissalDate());
        existing.setPerson(person);

        return mapper.toDto(repository.save(existing));
    }

    public void delete(Long id) {
        TemporaryEmployee employee = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor temporário não encontrado"));

        employee.setDeleted(true);
        repository.save(employee);
    }
}
