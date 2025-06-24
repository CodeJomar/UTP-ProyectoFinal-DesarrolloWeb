package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.SalarioMinimoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SalarioMinimoValidator.class)
public @interface SalarioMinimo {
    String message() default "El salario no puede ser menor a S/1130";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
