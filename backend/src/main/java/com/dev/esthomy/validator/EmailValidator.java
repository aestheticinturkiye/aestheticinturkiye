package com.dev.esthomy.validator;


import com.dev.esthomy.validator.aspect.ValidateEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;


import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidateEmail, String> {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(final String emailAddress, final ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(emailAddress)) return false;
        return pattern.matcher(emailAddress).matches();
    }
}