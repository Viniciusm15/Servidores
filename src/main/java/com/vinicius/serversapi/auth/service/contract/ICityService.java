package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.dto.city.CityRequestDto;
import com.vinicius.serversapi.auth.dto.city.CityResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService {
    CityResponseDto create(CityRequestDto dto);
    CityResponseDto getById(Long id);
    Page<CityResponseDto> getAll(Pageable pageable);
    CityResponseDto update(Long id, CityRequestDto dto);
    void delete(Long id);
}
