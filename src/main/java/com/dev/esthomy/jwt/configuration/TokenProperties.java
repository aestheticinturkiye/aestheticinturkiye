package com.dev.esthomy.jwt.configuration;

import lombok.Data;

import java.time.Duration;

@Data
public class TokenProperties {
    private Duration expiration;
    private JwtSecret secret;
}
