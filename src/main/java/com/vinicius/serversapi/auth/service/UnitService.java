package com.vinicius.serversapi.auth.service;

import com.vinicius.serversapi.auth.dto.unit.*;
import com.vinicius.serversapi.auth.mapper.UnitMapper;
import com.vinicius.serversapi.auth.model.core.Unit;
import com.vinicius.serversapi.auth.repository.UnitRepository;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository repository;
    private final UnitMapper mapper;

    public UnitResponseDto create(UnitRequestDto dto) {
        Unit entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public UnitResponseDto update(Long id, UnitRequestDto dto) {
        Unit entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        entity.setAcronym(dto.getAcronym());
        return mapper.toDto(repository.save(entity));
    }

    public UnitResponseDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    public Page<UnitResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public void delete(Long id) {
        Unit unit = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unidade não encontrada"));

        unit.setDeleted(true);
        repository.save(unit);
    }
}
