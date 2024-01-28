package com.dev.esthomy.jwt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokens {
    private Token accessToken;
    private Token refreshToken;
}
