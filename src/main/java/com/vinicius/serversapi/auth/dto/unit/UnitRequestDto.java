package com.vinicius.serversapi.auth.dto.unit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UnitRequestDto {

    private Long id;

    @NotBlank(message = "O nome da unidade é obrigatório.")
    @Size(max = 200, message = "O nome da unidade deve ter no máximo 200 caracteres.")
    private String name;

    @NotBlank(message = "A sigla da unidade é obrigatória.")
    @Size(max = 20, message = "A sigla deve ter no máximo 20 caracteres.")
    private String acronym;
}
