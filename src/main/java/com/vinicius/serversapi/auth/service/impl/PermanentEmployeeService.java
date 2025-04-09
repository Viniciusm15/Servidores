package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeResponseDto;
import com.vinicius.serversapi.auth.mapper.PermanentEmployeeMapper;
import com.vinicius.serversapi.auth.model.core.PermanentEmployee;
import com.vinicius.serversapi.auth.repository.PermanentEmployeeRepository;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.service.contract.IPermanentEmployeeService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermanentEmployeeService implements IPermanentEmployeeService {

    private final PermanentEmployeeRepository permanentEmployeeRepository;
    private final PermanentEmployeeMapper mapper;
    private final PersonRepository personRepository;

    public PermanentEmployeeResponseDto create(PermanentEmployeeRequestDto dto) {
        var person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        var entity = mapper.toEntity(dto, person);
        PermanentEmployee savedEntity = permanentEmployeeRepository.save(entity);

        return mapper.toDto(savedEntity);
    }

    public PermanentEmployeeResponseDto getById(Long id) {
        var entity = permanentEmployeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor efetivo não encontrado"));
        return mapper.toDto(entity);
    }

    public Page<PermanentEmployeeResponseDto> getAll(Pageable pageable) {
        return permanentEmployeeRepository.findAll(pageable)
                .map(mapper::toDto);
    }

    public PermanentEmployeeResponseDto update(Long id, PermanentEmployeeRequestDto dto) {
        var existing = permanentEmployeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor efetivo não encontrado"));

        existing.setRegistrationNumber(dto.getRegistrationNumber());
        return mapper.toDto(permanentEmployeeRepository.save(existing));
    }

    public void delete(Long id) {
        var employee = permanentEmployeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor efetivo não encontrado"));

        employee.setDeleted(true);
        permanentEmployeeRepository.save(employee);
    }
}
