package org.example.ws.email.validator.email;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+[@][a-z]{2,}[.][a-z]{2,4}$");

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(email)) {
            return true;
        }

        return pattern.matcher(email).matches();
    }
}