package com.legacy.barberia.validaciones;

import com.legacy.barberia.validaciones.anotaciones.PasswordFuerte;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordFuerteValidator implements ConstraintValidator<PasswordFuerte, String> {
    
    private static final String REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]-]).{8,}$";
    
    @Override
    public boolean isValid(String clave, ConstraintValidatorContext context) {
        if (clave == null || clave.isBlank()) return false;
        return clave.matches(REGEX);
    }
}
