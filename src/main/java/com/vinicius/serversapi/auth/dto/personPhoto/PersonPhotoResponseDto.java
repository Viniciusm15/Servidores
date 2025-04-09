package com.vinicius.serversapi.auth.dto.personPhoto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonPhotoResponseDto {

    private Long id;
    private Long personId;
    private LocalDate date;
    private String bucket;
    private String hash;
    private String photoUrl;
}
