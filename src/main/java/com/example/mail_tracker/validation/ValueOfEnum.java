package com.example.mail_tracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "Value must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
