package com.vinicius.serversapi.auth.dto.assignment;

import lombok.Data;

@Data
public class AssignmentResponseDto {
    private Long id;
    private String registrationNumber;
    private Long personId;
    private String personName;
    private Integer age;
    private String unitName;
    private String photoUrl;
}
