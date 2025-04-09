package com.vinicius.serversapi.auth.dto.personPhoto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonPhotoRequestDto {

    @NotNull(message = "O ID da pessoa é obrigatório.")
    private Long personId;

    @NotNull(message = "A data da foto é obrigatória.")
    private LocalDate date;

    @NotNull(message = "O bucket é obrigatório.")
    private String bucket;

    @NotNull(message = "O hash da imagem é obrigatório.")
    private String hash;
}
