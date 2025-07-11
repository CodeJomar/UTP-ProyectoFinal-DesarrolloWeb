package com.legacy.barberia.perfil.controller;


import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.perfil.model.Perfil;
import com.legacy.barberia.perfil.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilController {
    
    private final PerfilService perfilService;
    
    @GetMapping("")
    public String verPerfil(Model model) {
        try {
            Perfil perfil = perfilService.obtenerPerfilDeUsuarioAutenticado();
            model.addAttribute("perfil", perfil);
            return "Perfil-User";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el perfil: " + e.getMessage());
            return "error"; // Página de error personalizada
        }
    }
    
    @PostMapping("/guardar")
    public String guardarPerfil(@ModelAttribute Perfil perfil, RedirectAttributes redirectAttributes) {
        try {
            perfilService.guardarPerfil(perfil);
            redirectAttributes.addFlashAttribute("success", "Perfil actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el perfil: " + e.getMessage());
        }
        return "redirect:/perfil";
    }
}
