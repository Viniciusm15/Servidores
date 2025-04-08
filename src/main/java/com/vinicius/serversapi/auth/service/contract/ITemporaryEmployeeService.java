package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.employee.TemporaryEmployeeRequestDto;
import com.vinicius.serversapi.auth.dto.employee.TemporaryEmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITemporaryEmployeeService {
    TemporaryEmployeeResponseDto create(TemporaryEmployeeRequestDto dto);
    TemporaryEmployeeResponseDto getById(Long id);
    Page<TemporaryEmployeeResponseDto> getAll(Pageable pageable);
    TemporaryEmployeeResponseDto update(Long id, TemporaryEmployeeRequestDto dto);
    void delete(Long id);
}
