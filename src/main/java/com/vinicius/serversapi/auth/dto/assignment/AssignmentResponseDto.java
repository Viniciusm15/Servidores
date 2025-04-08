package com.vinicius.serversapi.auth.dto.assignment;

import lombok.Data;

@Data
public class AssignmentResponseDto {
    private Long id;
    private String personName;
    private String unitName;
}
