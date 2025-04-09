package com.vinicius.serversapi.auth.dto.assignment;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class AssignmentRequestDto {

    @NotNull(message = "O ID da pessoa é obrigatório.")
    private Long personId;

    @NotNull(message = "O ID da unidade é obrigatório.")
    private Long unitId;

    private LocalDate assignmentDate;
    private LocalDate removalDate;
    private String ordinanceNumber;
}
