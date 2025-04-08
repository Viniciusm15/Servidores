package com.vinicius.serversapi.auth.dto.person;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonRequestDto {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres.")
    private String name;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate birthDate;

    @NotBlank(message = "O sexo é obrigatório.")
    @Size(max = 9, message = "O sexo deve ter no máximo 9 caracteres.")
    private String gender;

    @NotBlank(message = "O nome da mãe é obrigatório.")
    @Size(max = 200, message = "O nome da mãe deve ter no máximo 200 caracteres.")
    private String motherName;

    @NotBlank(message = "O nome do pai é obrigatório.")
    @Size(max = 200, message = "O nome do pai deve ter no máximo 200 caracteres.")
    private String fatherName;
}
