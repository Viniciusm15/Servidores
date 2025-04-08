package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.assignment.*;
import com.vinicius.serversapi.auth.service.impl.AssignmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> create(@Valid @RequestBody AssignmentRequestDto dto) {
        return ResponseEntity.ok(assignmentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AssignmentResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(assignmentService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> update(@PathVariable Long id,
                                                        @Valid @RequestBody AssignmentRequestDto dto) {
        return ResponseEntity.ok(assignmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
