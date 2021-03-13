package org.example.ws.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class AnyNotNullValidator implements ConstraintValidator<AnyNotNull, Object> {

    public void initialize(AnyNotNull constraint) {

    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Field[] fields = value.getClass().getDeclaredFields();

        boolean result = false;
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(value) != null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                result = false;
            }
        }
        return result;
    }
}
