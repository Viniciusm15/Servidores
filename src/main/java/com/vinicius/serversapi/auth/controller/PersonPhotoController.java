package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.personPhoto.PersonPhotoResponseDto;
import com.vinicius.serversapi.auth.service.impl.PersonPhotoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/person-photos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PersonPhotoController {

    private final PersonPhotoService personPhotoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PersonPhotoResponseDto>> uploadMultiple(
            @RequestParam("personId") Long personId,
            @RequestPart("files") List<MultipartFile> files
    ) {
        return ResponseEntity.ok(personPhotoService.upload(personId, files));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonPhotoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personPhotoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PersonPhotoResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(personPhotoService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personPhotoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
