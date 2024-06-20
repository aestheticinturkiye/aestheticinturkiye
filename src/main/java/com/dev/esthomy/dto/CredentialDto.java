package com.dev.esthomy.dto;

import com.dev.esthomy.models.enums.MemberRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CredentialDto {
    private String email;
    private String password;
    private MemberRole role;
}
