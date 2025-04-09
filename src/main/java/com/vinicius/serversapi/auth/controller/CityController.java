package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.city.CityRequestDto;
import com.vinicius.serversapi.auth.dto.city.CityResponseDto;
import com.vinicius.serversapi.auth.service.impl.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Cidades", description = "Endpoints para gerenciamento de cidades")
public class CityController {

    private final CityService cityService;

    @Operation(summary = "Cadastrar nova cidade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityResponseDto> create(@RequestBody CityRequestDto dto) {
        return ResponseEntity.ok(cityService.create(dto));
    }

    @Operation(summary = "Buscar cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @Operation(summary = "Listar todas as cidades (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de cidades retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<CityResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(cityService.getAll(pageable));
    }

    @Operation(summary = "Atualizar cidade existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityResponseDto> update(
            @PathVariable Long id,
            @RequestBody CityRequestDto dto
    ) {
        return ResponseEntity.ok(cityService.update(id, dto));
    }

    @Operation(summary = "Remover cidade")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
