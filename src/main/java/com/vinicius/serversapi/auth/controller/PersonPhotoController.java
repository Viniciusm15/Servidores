package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.personPhoto.PersonPhotoResponseDto;
import com.vinicius.serversapi.auth.service.impl.PersonPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/person-photos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Fotos de Pessoas", description = "Endpoints para gerenciamento de fotos de pessoas")
public class PersonPhotoController {

    private final PersonPhotoService personPhotoService;

    @Operation(summary = "Enviar múltiplas fotos de uma pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fotos enviadas com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonPhotoResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PersonPhotoResponseDto>> uploadMultiple(
            @RequestParam("personId") Long personId,
            @RequestPart("files") List<MultipartFile> files
    ) {
        return ResponseEntity.ok(personPhotoService.upload(personId, files));
    }

    @Operation(summary = "Buscar foto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Foto encontrada"),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonPhotoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personPhotoService.getById(id));
    }

    @Operation(summary = "Listar fotos (paginado)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de fotos retornada com sucesso")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PersonPhotoResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(personPhotoService.getAll(pageable));
    }

    @Operation(summary = "Remover foto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Foto removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personPhotoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
