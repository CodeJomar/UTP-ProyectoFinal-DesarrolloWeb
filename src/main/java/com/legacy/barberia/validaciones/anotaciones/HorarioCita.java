package com.legacy.barberia.validaciones.anotaciones;

import com.legacy.barberia.validaciones.HorarioCitaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = HorarioCitaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HorarioCita {
    String message() default "La hora de la cita es entre las 7:00 y 22:00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

