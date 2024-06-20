package com.dev.esthomy.validator.aspect;

import com.dev.esthomy.validator.GSMValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {GSMValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface ValidateGSMNumber {
    String message() default "Invalid GSM number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
