package com.legacy.barberia.validaciones;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.empleado.model.dtos.RegisEmpleadoDTO;
import com.legacy.barberia.empleado.model.entities.Empleado;
import com.legacy.barberia.empleado.repository.EmpleadoRepository;
import com.legacy.barberia.validaciones.anotaciones.EmailUnicoEmpleado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailUnicoEmpleadoValidator implements ConstraintValidator<EmailUnicoEmpleado, RegisEmpleadoDTO> {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public boolean isValid(RegisEmpleadoDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getUsername() == null || dto.getUsername().isBlank()) {
            return true;
        }
        
        Optional<Empleado> existente = empleadoRepository.findByUsuarioEmpleadoUsername(dto.getUsername());
        
        if (empleadoRepository == null) {
            return true;
        }
        
        if (existente.isPresent()) {
            Long idExistente = existente.get().getId();         // id del empleado encontrado
            Long idActual = dto.getId();                        // id del empleado en edición
            
            boolean esElMismo = idActual != null && idExistente.equals(idActual);
            
            if (!esElMismo) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Este email ya está registrado")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            }
            
            return esElMismo;
        }
        
        return true; // No existe ningún empleado con ese email
    }
}