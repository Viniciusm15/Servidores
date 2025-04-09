package com.vinicius.serversapi.auth.service.impl;

import com.vinicius.serversapi.auth.dto.city.CityRequestDto;
import com.vinicius.serversapi.auth.dto.city.CityResponseDto;
import com.vinicius.serversapi.auth.mapper.CityMapper;
import com.vinicius.serversapi.auth.model.core.City;
import com.vinicius.serversapi.auth.repository.CityRepository;
import com.vinicius.serversapi.auth.service.contract.ICityService;
import com.vinicius.serversapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityResponseDto create(CityRequestDto dto) {
        City city = cityMapper.toEntity(dto);
        City saved = cityRepository.save(city);
        return cityMapper.toDto(saved);
    }

    public CityResponseDto getById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
        return cityMapper.toDto(city);
    }

    public Page<CityResponseDto> getAll(Pageable pageable) {
        return cityRepository.findAll(pageable).map(cityMapper::toDto);
    }

    public CityResponseDto update(Long id, CityRequestDto dto) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
        cityMapper.updateFromDto(dto, city);
        City updated = cityRepository.save(city);
        return cityMapper.toDto(updated);
    }

    public void delete(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
        city.setDeleted(true);
        cityRepository.save(city);
    }
}
