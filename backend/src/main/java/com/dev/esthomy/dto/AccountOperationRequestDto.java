package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountOperationRequestDto {
    private String name;
    private String surname;
    private int age;
    private String country;
    private String phone;
    private String password;
    private String gender;
    private String email;
    private List<String> operationId;
}
