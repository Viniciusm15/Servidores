package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.employee.*;
import com.vinicius.serversapi.auth.service.impl.PermanentEmployeeService;
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
@RequestMapping("/permanent-employees")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Servidores Efetivos", description = "Endpoints para gerenciamento de servidores efetivos")
public class PermanentEmployeeController {

    private final PermanentEmployeeService permanentEmployeeService;

    @Operation(summary = "Cadastrar servidor efetivo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermanentEmployeeResponseDto> create(@Valid @RequestBody PermanentEmployeeRequestDto dto) {
        return ResponseEntity.ok(permanentEmployeeService.create(dto));
    }

    @Operation(summary = "Buscar servidor efetivo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor encontrado"),
            @ApiResponse(responseCode = "404", description = "Servidor não encontrado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermanentEmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permanentEmployeeService.getById(id));
    }

    @Operation(summary = "Listar servidores efetivos (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de servidores retornada com sucesso")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PermanentEmployeeResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(permanentEmployeeService.getAll(pageable));
    }

    @Operation(summary = "Atualizar servidor efetivo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor não encontrado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermanentEmployeeResponseDto> update(@PathVariable Long id,
                                                               @Valid @RequestBody PermanentEmployeeRequestDto dto) {
        return ResponseEntity.ok(permanentEmployeeService.update(id, dto));
    }

    @Operation(summary = "Remover servidor efetivo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Servidor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor não encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permanentEmployeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
