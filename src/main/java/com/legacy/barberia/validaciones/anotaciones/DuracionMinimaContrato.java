package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.DuracionMinimaContratoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuracionMinimaContratoValidator.class)
public @interface DuracionMinimaContrato {
    String message() default "El contrato debe durar al menos 30 días";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

