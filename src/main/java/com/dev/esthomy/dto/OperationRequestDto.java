package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationRequestDto {
    private String clientName;
    private String email;
    private String phoneNumber;
    private String country;
    private int age;
}
