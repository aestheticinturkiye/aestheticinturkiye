package com.dev.esthomy.jwt.model;

import com.dev.esthomy.models.MemberRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtClaims {
    private MemberRole role;
    private String email;
    private String id;
}
