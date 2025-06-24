package com.legacy.barberia.validaciones;

import com.legacy.barberia.empleado.model.dtos.RegisEmpleadoDTO;
import com.legacy.barberia.validaciones.anotaciones.DuracionMinimaContrato;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DuracionMinimaContratoValidator implements ConstraintValidator<DuracionMinimaContrato, RegisEmpleadoDTO> {
    
    @Override
    public boolean isValid(RegisEmpleadoDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;
        
        LocalDate inicio = dto.getFechaContrato();
        LocalDate fin = dto.getFechaDespido();
        
        if (inicio == null || fin == null) return true;
        
        try {
            long dias = ChronoUnit.DAYS.between(inicio, fin);
            if (dias < 30) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("El contrato debe durar al menos 30 días")
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }
}

