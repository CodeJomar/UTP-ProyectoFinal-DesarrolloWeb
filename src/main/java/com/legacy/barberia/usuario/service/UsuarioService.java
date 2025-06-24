package com.legacy.barberia.usuario.service;

import com.legacy.barberia.usuario.model.Usuario;
import com.legacy.barberia.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }
    
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }
    
    public void eliminarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
    
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }
    
    public long contarCantidadUsuarios() {
        return usuarioRepo.count();
    }
}
