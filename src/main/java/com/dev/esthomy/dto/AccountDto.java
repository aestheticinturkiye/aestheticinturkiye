package com.dev.esthomy.dto;

import com.dev.esthomy.models.enums.MemberRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountDto {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String country;
    private String phone;
    private String password;
    private String gender;
    private String email;
    private MemberRole role;
}
