package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.address.AddressRequestDto;
import com.vinicius.serversapi.auth.dto.address.AddressResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAddressService {
    AddressResponseDto create(AddressRequestDto dto);
    AddressResponseDto getById(Long id);
    Page<AddressResponseDto> getAll(Pageable pageable);
    AddressResponseDto update(Long id, AddressRequestDto dto);
    void delete(Long id);
}