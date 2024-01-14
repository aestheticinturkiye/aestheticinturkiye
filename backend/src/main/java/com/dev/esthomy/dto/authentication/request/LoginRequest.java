package com.dev.esthomy.dto.authentication.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String emailAddress;
    private String password;
    private int role;
}
