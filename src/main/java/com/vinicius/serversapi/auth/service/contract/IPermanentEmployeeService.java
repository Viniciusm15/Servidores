package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.PermanentEmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPermanentEmployeeService {
    PermanentEmployeeResponseDto create(PermanentEmployeeRequestDto dto);
    PermanentEmployeeResponseDto getById(Long id);
    Page<PermanentEmployeeResponseDto> getAll(Pageable pageable);
    PermanentEmployeeResponseDto update(Long id, PermanentEmployeeRequestDto dto);
    void delete(Long id);
}
