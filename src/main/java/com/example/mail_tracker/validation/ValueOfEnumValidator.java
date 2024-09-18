package com.example.mail_tracker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return acceptedValues.contains(s);
    }
}
