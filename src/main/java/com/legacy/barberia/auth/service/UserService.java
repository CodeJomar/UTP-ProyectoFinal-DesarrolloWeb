package com.legacy.barberia.auth.service;

import com.legacy.barberia.auth.model.dtos.RegisUserDTO;
import com.legacy.barberia.auth.model.entities.Role;
import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.model.enums.RoleList;
import com.legacy.barberia.auth.repository.RoleRepository;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.perfil.model.Perfil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void registrarUsuario(RegisUserDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        
        Role defaultRole = roleRepository.findAll().stream()
            .filter(r -> r.getName().equals(RoleList.ROLE_USER))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró el rol por defecto"));
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(defaultRole);
        
        userRepository.save(user);
    }
    
    public List<User> listadoUsuarios() {
        return userRepository.findAll();
    }
    
    public Optional<User> buscarPorId(String id) {
        return userRepository.findById(id);
    }
    
    public void eliminarUsuario(String id) {
        userRepository.deleteById(id);
    }
    
//    public void actualizarUsuario(User user) {
//        userRepository.save(user);
//    }
}
