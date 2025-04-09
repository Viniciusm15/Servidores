package com.vinicius.serversapi.auth.controller;

import com.vinicius.serversapi.auth.dto.address.AddressRequestDto;
import com.vinicius.serversapi.auth.dto.address.AddressResponseDto;
import com.vinicius.serversapi.auth.service.impl.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDto> create(@RequestBody AddressRequestDto dto) {
        return ResponseEntity.ok(addressService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AddressResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(addressService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> update(
            @PathVariable Long id,
            @RequestBody AddressRequestDto dto
    ) {
        return ResponseEntity.ok(addressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
