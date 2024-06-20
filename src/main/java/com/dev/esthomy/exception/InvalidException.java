package com.dev.esthomy.exception;

import com.dev.esthomy.exception.error.InvalidError;
import lombok.Data;

@Data
public class InvalidException extends RuntimeException {
    private final InvalidError invalidError;
}
