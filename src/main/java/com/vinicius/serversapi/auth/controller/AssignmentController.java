package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.assignment.*;
import com.vinicius.serversapi.auth.service.impl.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Lotação", description = "Endpoints relacionados à lotação de servidores")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @Operation(summary = "Cadastrar nova lotação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lotação cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssignmentResponseDto> create(@Valid @RequestBody AssignmentRequestDto dto) {
        return ResponseEntity.ok(assignmentService.create(dto));
    }

    @Operation(summary = "Buscar lotação por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lotação encontrada"),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AssignmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getById(id));
    }

    @Operation(summary = "Listar todas as lotações (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de lotações retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<AssignmentResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(assignmentService.getAll(pageable));
    }

    @Operation(summary = "Listar servidores efetivos por unidade (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidores efetivos listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/permanent-employees/by-unit/{unitId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<AssignmentResponseDto>> getPermanentEmployeesByUnit(
            @PathVariable Long unitId,
            Pageable pageable) {
        AssignmentRequestDto dto = new AssignmentRequestDto();
        dto.setUnitId(unitId);
        return ResponseEntity.ok(assignmentService.getPermanentEmployeesByUnit(dto, pageable));
    }

    @Operation(summary = "Atualizar lotação existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lotação atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssignmentResponseDto> update(@PathVariable Long id,
                                                        @Valid @RequestBody AssignmentRequestDto dto) {
        return ResponseEntity.ok(assignmentService.update(id, dto));
    }

    @Operation(summary = "Remover lotação")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Lotação removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
