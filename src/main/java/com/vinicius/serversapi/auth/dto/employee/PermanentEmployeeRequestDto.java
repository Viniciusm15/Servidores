package com.vinicius.serversapi.auth.dto.employee;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class PermanentEmployeeRequestDto {

    @NotNull(message = "O ID da pessoa é obrigatório.")
    private Long personId;

    @NotNull(message = "A matrícula é obrigatória.")
    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres.")
    private String registrationNumber;
}
