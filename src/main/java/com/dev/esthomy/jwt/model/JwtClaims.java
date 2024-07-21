package com.dev.esthomy.jwt.model;

import com.dev.esthomy.models.enums.MemberRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class JwtClaims {
    private MemberRole role;
    private String email;
    private UUID id;
}
