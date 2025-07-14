package com.legacy.barberia.validaciones;

import com.legacy.barberia.auth.model.dtos.RegisUserDTO;
import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.validaciones.anotaciones.EmailUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, RegisUserDTO> {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public boolean isValid(RegisUserDTO usuario, ConstraintValidatorContext context) {
        if (usuario == null || usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            return true;
        }
        
        String username = usuario.getUsername();
        String userId = usuario.getId();
        
        Optional<User> usuarioExistente = userRepository.findByUsername(username);
        
        if (userRepository == null) {
            return true;
        }
        
        if (usuarioExistente.isPresent()) {
            boolean esMismoUsuario = userId != null && usuarioExistente.get().getId().equals(userId);
            if (!esMismoUsuario) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context
                        .getDefaultConstraintMessageTemplate())
                        .addPropertyNode("username")
                        .addConstraintViolation();
            }
            System.out.println(esMismoUsuario);
            return esMismoUsuario;
        }
        return true;
    }
}