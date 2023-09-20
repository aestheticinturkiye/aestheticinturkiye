package com.dev.esthomy.security;

import org.springframework.http.HttpStatus;

public class LoginExceptions extends Throwable {
    public LoginExceptions(HttpStatus httpStatus, String invalidJwtToken) {

    }
}
