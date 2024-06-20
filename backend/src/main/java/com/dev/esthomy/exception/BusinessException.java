package com.dev.esthomy.exception;

import com.dev.esthomy.exception.error.BusinessError;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private final BusinessError businessError;

    public BusinessException(final BusinessError businessError) {
        super(businessError.getMessage());
        this.businessError = businessError;
    }

}
