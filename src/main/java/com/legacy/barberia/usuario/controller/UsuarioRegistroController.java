package com.legacy.barberia.usuario.controller;

import com.legacy.barberia.usuario.model.Usuario;
import com.legacy.barberia.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario-registro")
public class UsuarioRegistroController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("")
    public String mostrarPaginaRegistroUsuarios(Model model) {
        model.addAttribute("usuario", new Usuario()); // ← Para Usuario
        model.addAttribute("usuarios", usuarioService.listarUsuarios()); // ← Para Usuario
        model.addAttribute("totalUsuarios", usuarioService.contarCantidadUsuarios()); // ← Para Usuario
        
        return "usuario-form-list";
    }
    
    @PostMapping("/guardarUsuario")
    public String guardarUsuarios(@Valid @ModelAttribute Usuario usuario,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            model.addAttribute("totalUsuarios", usuarioService.contarCantidadUsuarios());
            return "usuario-form-list";
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario-registro";
    }
    
    @GetMapping("/eliminarUsuario")
    public String eliminarUsuarios(@RequestParam Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuario-registro";
    }
    
    @GetMapping("/editarUsuario")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("totalUsuarios", usuarioService.contarCantidadUsuarios());
        
        return "usuario-form-list";
    }
}
