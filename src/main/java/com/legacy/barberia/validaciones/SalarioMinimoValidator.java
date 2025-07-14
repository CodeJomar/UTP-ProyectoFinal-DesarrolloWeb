package com.legacy.barberia.validaciones;

import com.legacy.barberia.validaciones.anotaciones.SalarioMinimo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class SalarioMinimoValidator implements ConstraintValidator<SalarioMinimo, BigDecimal> {
    
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        // Si es null, que lo valide @NotNull
        if (value == null) return true;
        
        // Si es negativo o cero, que lo valide @Positive
        if (value.compareTo(BigDecimal.ZERO) <= 0) return true;
        
        // Aquí sí valida salario mínimo
        return value.compareTo(new BigDecimal("1160")) >= 0;
    }
}
