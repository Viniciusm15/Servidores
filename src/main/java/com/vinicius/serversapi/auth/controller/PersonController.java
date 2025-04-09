package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.person.PersonRequestDto;
import com.vinicius.serversapi.auth.dto.person.PersonResponseDto;
import com.vinicius.serversapi.auth.service.contract.IPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "Endpoints relacionados ao gerenciamento de pessoas")
public class PersonController {

    private final IPersonService personService;

    @Operation(summary = "Cadastrar nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonResponseDto> create(@RequestBody @Valid PersonRequestDto dto) {
        var person = personService.createAndReturnEntity(dto);
        return ResponseEntity.ok(personService.getById(person.getId()));
    }

    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @Operation(summary = "Listar todas as pessoas (paginado)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PersonResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(personService.getAll(pageable));
    }

    @Operation(summary = "Atualizar uma pessoa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonResponseDto> update(@PathVariable Long id, @RequestBody @Valid PersonRequestDto dto) {
        return ResponseEntity.ok(personService.update(id, dto));
    }

    @Operation(summary = "Remover pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
