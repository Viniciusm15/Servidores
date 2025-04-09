package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.address.AddressRequestDto;
import com.vinicius.serversapi.auth.dto.address.AddressResponseDto;
import com.vinicius.serversapi.auth.mapper.AddressMapper;
import com.vinicius.serversapi.auth.model.core.Address;
import com.vinicius.serversapi.auth.model.core.City;
import com.vinicius.serversapi.auth.repository.AddressRepository;
import com.vinicius.serversapi.auth.repository.CityRepository;
import com.vinicius.serversapi.auth.service.contract.IAddressService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final AddressMapper addressMapper;

    public AddressResponseDto create(AddressRequestDto dto) {
        Address address = addressMapper.toEntity(dto);

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));

        address.setCity(city);

        Address saved = addressRepository.save(address);
        return addressMapper.toDto(saved);
    }

    public AddressResponseDto getById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        return addressMapper.toDto(address);
    }

    public Page<AddressResponseDto> getAll(Pageable pageable) {
        return addressRepository.findAll(pageable).map(addressMapper::toDto);
    }

    public AddressResponseDto update(Long id, AddressRequestDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

        addressMapper.updateFromDto(dto, address);

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));

        address.setCity(city);

        Address updated = addressRepository.save(address);
        return addressMapper.toDto(updated);
    }

    public void delete(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        address.setDeleted(true);
        addressRepository.save(address);
    }
}
