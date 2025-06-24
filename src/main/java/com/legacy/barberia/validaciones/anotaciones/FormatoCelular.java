package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.FormatoCelularValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FormatoCelularValidator.class)
public @interface FormatoCelular {
    String message() default "El celular debe tener el formato 999-999-999";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

