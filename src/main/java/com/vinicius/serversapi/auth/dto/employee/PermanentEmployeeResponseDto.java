package com.vinicius.serversapi.auth.dto.employee;

import lombok.Data;

@Data
public class PermanentEmployeeResponseDto {
    private Long id;
    private String registrationNumber;
    private Long personId;
    private String personName;
    private Integer age;
    private String unitName;
    private String photoUrl;
}
