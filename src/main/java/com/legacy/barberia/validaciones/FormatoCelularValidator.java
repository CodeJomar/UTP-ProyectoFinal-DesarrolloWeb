package com.legacy.barberia.validaciones;

import com.legacy.barberia.validaciones.anotaciones.FormatoCelular;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FormatoCelularValidator implements ConstraintValidator<FormatoCelular, String> {
    
    private static final String REGEX = "^\\d{3}-\\d{3}-\\d{3}$";
    
    @Override
    public boolean isValid(String celular, ConstraintValidatorContext context) {
        return celular == null || celular.matches(REGEX);
    }
}
