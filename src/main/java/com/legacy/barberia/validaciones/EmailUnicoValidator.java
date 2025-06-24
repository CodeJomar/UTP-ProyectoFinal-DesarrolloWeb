package com.legacy.barberia.validaciones;

import com.legacy.barberia.usuario.model.Usuario;
import com.legacy.barberia.usuario.repository.UsuarioRepository;
import com.legacy.barberia.validaciones.anotaciones.EmailUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, Usuario> {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        if (usuario == null || usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            return true;
        }
        
        String email = usuario.getEmail();
        Long userId = usuario.getId();
        
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
        
        if (usuarioRepository == null) {
            return true;
        }
        
        if (usuarioExistente.isPresent()) {
            boolean esMismoUsuario = userId != null && usuarioExistente.get().getId().equals(userId);
            if (!esMismoUsuario) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context
                        .getDefaultConstraintMessageTemplate())
                        .addPropertyNode("email")
                        .addConstraintViolation();
            }
            System.out.println(esMismoUsuario);
            return esMismoUsuario;
        }
        return true;
    }
}