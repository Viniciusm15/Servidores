package com.vinicius.serversapi.auth.service;

import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeResponseDto;
import com.vinicius.serversapi.auth.model.core.PermanentEmployee;
import com.vinicius.serversapi.auth.mapper.PermanentEmployeeMapper;
import com.vinicius.serversapi.auth.repository.PermanentEmployeeRepository;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PermanentEmployeeService {

    private final PermanentEmployeeRepository repository;
    private final PermanentEmployeeMapper mapper;
    private final PersonRepository personRepository;

    public PermanentEmployeeResponseDto create(PermanentEmployeeRequestDto dto) {
        var person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        var entity = mapper.toEntity(dto, person);

        return mapper.toDto(repository.save(entity));
    }

    public PermanentEmployeeResponseDto getById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor efetivo não encontrado"));
        return mapper.toDto(entity);
    }

    public Page<PermanentEmployeeResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public PermanentEmployeeResponseDto update(Long id, PermanentEmployeeRequestDto dto) {
        var existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Servidor efetivo não encontrado"));

        existing.setRegistrationNumber(dto.getRegistrationNumber());
        return mapper.toDto(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Servidor efetivo não encontrado");
        }
        repository.deleteById(id);
    }
}
