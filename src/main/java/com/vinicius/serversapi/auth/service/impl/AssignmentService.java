package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.assignment.*;
import com.vinicius.serversapi.auth.service.contract.IAssignmentService;
import com.vinicius.serversapi.exception.NotFoundException;
import com.vinicius.serversapi.auth.mapper.AssignmentMapper;
import com.vinicius.serversapi.auth.model.core.Assignment;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.Unit;
import com.vinicius.serversapi.auth.repository.AssignmentRepository;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final PersonRepository personRepository;
    private final UnitRepository unitRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentResponseDto create(AssignmentRequestDto dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new NotFoundException("Unidade não encontrada"));

        Assignment assignment = assignmentMapper.toEntity(dto, person, unit);
        Assignment saved = assignmentRepository.save(assignment);

        return assignmentMapper.toDto(saved);
    }

    public AssignmentResponseDto getById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lotação não encontrada"));

        return assignmentMapper.toDto(assignment);
    }

    public Page<AssignmentResponseDto> getAll(Pageable pageable) {
        return assignmentRepository.findAll(pageable)
                .map(assignmentMapper::toDto);
    }

    public AssignmentResponseDto update(Long id, AssignmentRequestDto dto) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lotação não encontrada"));

        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new NotFoundException("Unidade não encontrada"));

        assignment.setPerson(person);
        assignment.setUnit(unit);

        Assignment updated = assignmentRepository.save(assignment);
        return assignmentMapper.toDto(updated);
    }

    public void delete(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lotação não encontrada"));

        assignment.setDeleted(true);
        assignmentRepository.save(assignment);
    }
}
