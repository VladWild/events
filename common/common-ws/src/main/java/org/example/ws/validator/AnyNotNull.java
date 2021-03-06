package org.example.ws.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AnyNotNullValidator.class)
public @interface AnyNotNull {
    String message() default "must be any not null field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}