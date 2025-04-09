package com.vinicius.serversapi.auth.dto.employee;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TemporaryEmployeeRequestDto {

    @NotNull(message = "A data de admissão é obrigatória.")
    @FutureOrPresent(message = "A data de admissão não pode ser no passado.")
    private LocalDate admissionDate;

    private LocalDate dismissalDate;

    @NotNull(message = "O ID da pessoa é obrigatório.")
    private Long personId;
}
