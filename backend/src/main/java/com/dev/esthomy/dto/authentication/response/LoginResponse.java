package com.dev.esthomy.dto.authentication.response;

import com.dev.esthomy.jwt.model.JwtTokens;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String status;
}
