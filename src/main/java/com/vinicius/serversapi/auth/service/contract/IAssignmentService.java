package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.assignment.AssignmentRequestDto;
import com.vinicius.serversapi.auth.dto.assignment.AssignmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAssignmentService {
    AssignmentResponseDto create(AssignmentRequestDto dto);
    AssignmentResponseDto getById(Long id);
    Page<AssignmentResponseDto> getAll(Pageable pageable);
    AssignmentResponseDto update(Long id, AssignmentRequestDto dto);
    void delete(Long id);
}
