package com.legacy.barberia.validaciones;

import com.legacy.barberia.validaciones.anotaciones.HorarioCita;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class HorarioCitaValidator implements ConstraintValidator<HorarioCita, LocalTime> {
    @Override
    public boolean isValid(LocalTime hora, ConstraintValidatorContext constraintValidatorContext) {
        if (hora == null) {
            return true;
        }
        return !hora.isBefore(LocalTime.of(7,0)) & !hora.isAfter(LocalTime.of(22,0));
    }
}
