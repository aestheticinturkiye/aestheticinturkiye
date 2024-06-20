package com.dev.esthomy.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidError {
    public static final InvalidError INVALID_ERROR = new InvalidError("INVALID_ERROR", "An error occurred. Please try again later.", "Invalid Error");
    private String code;
    private String message;
    private String description;
}
