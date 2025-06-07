package com.web.barberia.controller;

import com.web.barberia.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerUsu {
    List<Usuario> usuarios = new ArrayList<>();
    
    @GetMapping("/login")
    public String pageLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
    
    @PostMapping("/tablaUsuarios")
    public String saveUser(@ModelAttribute Usuario usuario) {
        usuarios.add(usuario);
        return "redirect:/tablaUsuarios";
    }
    
    @GetMapping("/tablaUsuarios")
    public String pageTablaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "tablaUsuarios";
    }
}
