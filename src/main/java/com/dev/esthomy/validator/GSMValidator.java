package com.dev.esthomy.validator;

import com.dev.esthomy.validator.aspect.ValidateGSMNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class GSMValidator implements ConstraintValidator<ValidateGSMNumber, String> {

    private static final String GSM_PATTERN = "^\\d{10}$";

    private static final Pattern pattern = Pattern.compile(GSM_PATTERN);

    @Override
    public boolean isValid(final String gsmNumber, final ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(gsmNumber)) return false;
        return pattern.matcher(gsmNumber).matches();
    }
}