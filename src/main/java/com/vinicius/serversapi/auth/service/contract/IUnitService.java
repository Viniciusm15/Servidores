package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.unit.UnitRequestDto;
import com.vinicius.serversapi.auth.dto.unit.UnitResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUnitService {
    UnitResponseDto create(UnitRequestDto dto);
    UnitResponseDto update(Long id, UnitRequestDto dto);
    UnitResponseDto getById(Long id);
    Page<UnitResponseDto> getAll(Pageable pageable);
    void delete(Long id);
}
