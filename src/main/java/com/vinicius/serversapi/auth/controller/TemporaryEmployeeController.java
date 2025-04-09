package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.employee.*;
import com.vinicius.serversapi.auth.service.impl.TemporaryEmployeeService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temporary-employees")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Servidores Temporários", description = "Endpoints para gerenciamento de servidores temporários")
public class TemporaryEmployeeController {

    private final TemporaryEmployeeService temporaryEmployeeService;

    @Operation(summary = "Cadastrar servidor temporário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor temporário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TemporaryEmployeeResponseDto> create(@Valid @RequestBody TemporaryEmployeeRequestDto dto) {
        return ResponseEntity.ok(temporaryEmployeeService.create(dto));
    }

    @Operation(summary = "Buscar servidor temporário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor temporário encontrado"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryEmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(temporaryEmployeeService.getById(id));
    }

    @Operation(summary = "Listar servidores temporários (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de servidores temporários retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<TemporaryEmployeeResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(temporaryEmployeeService.getAll(pageable));
    }

    @Operation(summary = "Atualizar servidor temporário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor temporário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryEmployeeResponseDto> update(@PathVariable Long id,
                                                               @Valid @RequestBody TemporaryEmployeeRequestDto dto) {
        return ResponseEntity.ok(temporaryEmployeeService.update(id, dto));
    }

    @Operation(summary = "Remover servidor temporário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Servidor temporário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        temporaryEmployeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
