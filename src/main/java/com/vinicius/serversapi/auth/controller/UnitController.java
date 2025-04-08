package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.unit.*;
import com.vinicius.serversapi.auth.service.UnitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/units")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UnitController {
    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<UnitResponseDto> create(@Valid @RequestBody UnitRequestDto dto) {
        return ResponseEntity.ok(unitService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitResponseDto> update(@PathVariable Long id, @Valid @RequestBody UnitRequestDto dto) {
        return ResponseEntity.ok(unitService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UnitResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(unitService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
