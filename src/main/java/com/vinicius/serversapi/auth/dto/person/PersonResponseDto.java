package com.vinicius.serversapi.auth.dto.person;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonResponseDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String motherName;
    private String fatherName;
}
