package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.EmailUnicoEmpleadoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailUnicoEmpleadoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnicoEmpleado {
    String message() default "Este email ya está registrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}