package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.employee.*;
import com.vinicius.serversapi.auth.service.impl.PermanentEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permanent-employees")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PermanentEmployeeController {

    private final PermanentEmployeeService permanentEmployeeService;

    @PostMapping
    public ResponseEntity<PermanentEmployeeResponseDto> create(@Valid @RequestBody PermanentEmployeeRequestDto dto) {
        return ResponseEntity.ok(permanentEmployeeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermanentEmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permanentEmployeeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PermanentEmployeeResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(permanentEmployeeService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermanentEmployeeResponseDto> update(@PathVariable Long id,
                                                               @Valid @RequestBody PermanentEmployeeRequestDto dto) {

        return ResponseEntity.ok(permanentEmployeeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permanentEmployeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
