package com.dev.esthomy.dto.authentication.response;

public enum LoginResponseStatus {
    SUCCESS("Success"),
    FAILURE("Failure");

    private final String description;

    LoginResponseStatus(String description) {
        this.description = description;
    }

    public String getValue() {
        return description;
    }
}
