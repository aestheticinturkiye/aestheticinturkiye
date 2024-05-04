package com.dev.esthomy.dto.authentication.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateWithRefreshTokenResponse {
    private String accessToken;
}
