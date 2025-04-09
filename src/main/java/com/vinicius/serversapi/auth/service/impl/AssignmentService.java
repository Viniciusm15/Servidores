package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.assignment.*;
import com.vinicius.serversapi.auth.model.core.PermanentEmployee;
import com.vinicius.serversapi.auth.service.contract.IAssignmentService;
import com.vinicius.serversapi.exception.NotFoundException;
import com.vinicius.serversapi.auth.mapper.AssignmentMapper;
import com.vinicius.serversapi.auth.model.core.Assignment;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.model.core.Unit;
import com.vinicius.serversapi.auth.repository.AssignmentRepository;
import com.vinicius.serversapi.auth.repository.PersonRepository;
import com.vinicius.serversapi.auth.repository.UnitRepository;
import com.vinicius.serversapi.utils.DateUtils;
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
    private final PersonPhotoService personPhotoService;

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

    public Unit getActiveUnitForPerson(Person person, Long unitId) {
        return person.getAssignmentList().stream()
                .filter(a -> a.getUnit().getId().equals(unitId))
                .filter(a -> a.getRemovalDate() == null)
                .map(Assignment::getUnit)
                .findFirst()
                .orElse(null);
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
        assignment.setAssignmentDate(dto.getAssignmentDate());
        assignment.setRemovalDate(dto.getRemovalDate());
        assignment.setOrdinance(dto.getOrdinanceNumber());

        Assignment updated = assignmentRepository.save(assignment);
        return assignmentMapper.toDto(updated);
    }

    public Page<AssignmentResponseDto> getPermanentEmployeesByUnit(AssignmentRequestDto dto, Pageable pageable) {
        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new NotFoundException("Unidade não encontrada"));

        return assignmentRepository
                .findActiveWithPermanentEmployeeByUnitId(unit.getId(), pageable)
                .map(assignment -> {
                    Person person = assignment.getPerson();

                    PermanentEmployee employee = person.getPermanentEmployee();
                    if (employee == null) {
                        throw new NotFoundException("Servidor efetivo não encontrado para a pessoa: " + person.getId());
                    }

                    AssignmentResponseDto responseDto = new AssignmentResponseDto();
                    responseDto.setId(employee.getId());
                    responseDto.setRegistrationNumber(employee.getRegistrationNumber());
                    responseDto.setPersonId(person.getId());
                    responseDto.setPersonName(person.getName());
                    responseDto.setAge(DateUtils.calcularIdade(person.getBirthDate()));
                    responseDto.setUnitName(unit.getName());
                    responseDto.setPhotoUrl(personPhotoService.getPersonPhotoUrl(person));

                    return responseDto;
                });
    }

    public void delete(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lotação não encontrada"));

        assignment.setDeleted(true);
        assignmentRepository.save(assignment);
    }
}
