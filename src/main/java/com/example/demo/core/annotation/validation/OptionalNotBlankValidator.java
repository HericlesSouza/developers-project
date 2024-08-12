package com.example.demo.core.annotation.validation;

import com.example.demo.core.annotation.OptionalNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class OptionalNotBlankValidator implements ConstraintValidator<OptionalNotBlank, String> {

    @Override
    public void initialize(OptionalNotBlank annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }
}
