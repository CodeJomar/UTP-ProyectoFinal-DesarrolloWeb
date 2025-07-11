package com.legacy.barberia.perfil.service;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.perfil.model.Perfil;
import com.legacy.barberia.perfil.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {
    
    private final PerfilRepository perfilRepository;
    private final UserRepository userRepository;
    
    public Perfil obtenerPerfilDeUsuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        return perfilRepository.findByUserId(user.getId())
            .orElseGet(() -> {
                Perfil nuevo = new Perfil();
                nuevo.setUser(user);
                nuevo.setNombre("");
                nuevo.setTelefono("");
                nuevo.setDireccion("");
                return perfilRepository.save(nuevo);
            });
    }
    
    public void guardarPerfil(Perfil perfilForm) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        Perfil perfil = perfilRepository.findByUserId(user.getId())
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        perfil.setNombre(perfilForm.getNombre() != null ? perfilForm.getNombre().trim() : "");
        perfil.setTelefono(perfilForm.getTelefono() != null ? perfilForm.getTelefono().trim() : "");
        perfil.setDireccion(perfilForm.getDireccion() != null ? perfilForm.getDireccion().trim() : "");
        
        perfilRepository.save(perfil);
    }
}

