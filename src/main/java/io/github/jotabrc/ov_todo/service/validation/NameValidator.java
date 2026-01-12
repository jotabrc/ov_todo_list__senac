package io.github.jotabrc.ov_todo.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class NameValidator implements ConstraintValidator<ValidateName, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return nonNull(s) && !s.isBlank();
    }
}
