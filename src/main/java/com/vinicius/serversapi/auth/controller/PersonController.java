package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.person.PersonRequestDto;
import com.vinicius.serversapi.auth.dto.person.PersonResponseDto;
import com.vinicius.serversapi.auth.service.contract.IPersonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponseDto> create(@RequestBody @Valid PersonRequestDto dto) {
        var person = personService.createAndReturnEntity(dto);
        return ResponseEntity.ok(personService.getById(person.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(personService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> update(@PathVariable Long id, @RequestBody @Valid PersonRequestDto dto) {
        return ResponseEntity.ok(personService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
