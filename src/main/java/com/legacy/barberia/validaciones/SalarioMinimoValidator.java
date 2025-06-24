package com.legacy.barberia.validaciones;

import com.legacy.barberia.validaciones.anotaciones.SalarioMinimo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class SalarioMinimoValidator implements ConstraintValidator<SalarioMinimo, BigDecimal> {
    
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value != null && value.compareTo(new BigDecimal("1150")) >= 0;
    }
}
