package com.dev.esthomy.dto.request.accountRequests;

import com.dev.esthomy.validator.aspect.ValidateEmail;
import com.dev.esthomy.validator.aspect.ValidateGSMNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateAccountRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(value = 18, message = "age must be greater than 18")
    private int age;
    @NotBlank
    private String country;
    @ValidateGSMNumber
    private String phone;
    @NotBlank
    private String password;
    @NotBlank(message = "gender is mandatory")
    private String gender;
    @ValidateEmail
    private String email;
}
