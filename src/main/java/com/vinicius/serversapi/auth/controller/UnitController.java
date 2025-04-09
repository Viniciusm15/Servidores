package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.unit.*;
import com.vinicius.serversapi.auth.service.impl.UnitService;
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
@RequestMapping("/units")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Unidades", description = "Endpoints para gerenciamento de unidades")
public class UnitController {

    private final UnitService unitService;

    @Operation(summary = "Cadastrar unidade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UnitResponseDto> create(@Valid @RequestBody UnitRequestDto dto) {
        return ResponseEntity.ok(unitService.create(dto));
    }

    @Operation(summary = "Atualizar unidade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UnitResponseDto> update(@PathVariable Long id, @Valid @RequestBody UnitRequestDto dto) {
        return ResponseEntity.ok(unitService.update(id, dto));
    }

    @Operation(summary = "Buscar unidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UnitResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getById(id));
    }

    @Operation(summary = "Listar unidades (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de unidades retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<UnitResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(unitService.getAll(pageable));
    }

    @Operation(summary = "Remover unidade")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Unidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
