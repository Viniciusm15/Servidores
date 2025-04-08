package com.vinicius.serversapi.auth.dto.employee;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TemporaryEmployeeResponseDto {
    private Long id;
    private LocalDate admissionDate;
    private LocalDate dismissalDate;
    private String personName;
}
