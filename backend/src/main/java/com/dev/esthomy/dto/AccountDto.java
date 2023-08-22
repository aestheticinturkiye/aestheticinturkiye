package com.dev.esthomy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDto {
    private String name;
    private String surname;
    private int age;
    private String country;
    private String phone;
    private String password;
    private String gender;
    private String email;
    @JsonIgnoreProperties("userAccount")
    private List<OperationRequestDto> operationRequestDtos;
}
