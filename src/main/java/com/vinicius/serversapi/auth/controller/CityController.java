package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.city.CityRequestDto;
import com.vinicius.serversapi.auth.dto.city.CityResponseDto;
import com.vinicius.serversapi.auth.service.impl.CityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponseDto> create(@RequestBody CityRequestDto dto) {
        return ResponseEntity.ok(cityService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CityResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(cityService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDto> update(
            @PathVariable Long id,
            @RequestBody CityRequestDto dto
    ) {
        return ResponseEntity.ok(cityService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
