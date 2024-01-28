package com.dev.esthomy.dto.authentication.request;

import lombok.Builder;

@Builder
public class AuthenticateWithRefreshTokenResponse {
    private String accessToken;
}
