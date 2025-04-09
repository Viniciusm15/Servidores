package com.vinicius.serversapi.auth.dto.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityRequestDto {

    @NotBlank(message = "O nome da cidade é obrigatório.")
    @Size(max = 200, message = "O nome da cidade deve ter no máximo 200 caracteres.")
    private String name;

    @NotBlank(message = "A sigla do estado é obrigatória.")
    @Size(max = 2, message = "A sigla do estado deve ter 2 caracteres.")
    private String state;
}
