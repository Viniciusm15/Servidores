package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.employee.*;
import com.vinicius.serversapi.auth.service.impl.TemporaryEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temporary-employees")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TemporaryEmployeeController {

    private final TemporaryEmployeeService temporaryEmployeeService;

    @PostMapping
    public ResponseEntity<TemporaryEmployeeResponseDto> create(@Valid @RequestBody TemporaryEmployeeRequestDto dto) {
        return ResponseEntity.ok(temporaryEmployeeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemporaryEmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(temporaryEmployeeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TemporaryEmployeeResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(temporaryEmployeeService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemporaryEmployeeResponseDto> update(@PathVariable Long id,
                                                               @Valid @RequestBody TemporaryEmployeeRequestDto dto) {
        return ResponseEntity.ok(temporaryEmployeeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        temporaryEmployeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
