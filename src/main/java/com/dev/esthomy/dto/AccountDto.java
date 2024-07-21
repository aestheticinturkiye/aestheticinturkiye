package com.dev.esthomy.dto;

import com.dev.esthomy.models.enums.MemberRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private String id;
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
