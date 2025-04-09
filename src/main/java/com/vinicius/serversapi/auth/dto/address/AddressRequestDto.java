package com.vinicius.serversapi.auth.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequestDto {

    @NotBlank(message = "O tipo de logradouro é obrigatório.")
    @Size(max = 50, message = "O tipo de logradouro deve ter no máximo 50 caracteres.")
    private String streetType;

    @NotBlank(message = "O nome do logradouro é obrigatório.")
    @Size(max = 200, message = "O nome do logradouro deve ter no máximo 200 caracteres.")
    private String streetName;

    @NotBlank(message = "O número é obrigatório.")
    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
    private String number;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String neighborhood;

    @NotNull(message = "O ID da cidade é obrigatório.")
    private Long cityId;
}
