package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.PasswordFuerteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordFuerteValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordFuerte {
    String message() default "La contraseña debe contener: [8 caracteres, una Mayúscula, un Número y un Carácter Especial]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
