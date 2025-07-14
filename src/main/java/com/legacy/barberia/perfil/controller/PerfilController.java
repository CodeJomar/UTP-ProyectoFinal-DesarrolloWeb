package com.legacy.barberia.perfil.controller;


import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.perfil.model.Perfil;
import com.legacy.barberia.perfil.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilController {
    
    private final PerfilService perfilService;
    
    @GetMapping("")
    public String verPerfil(Model model) {
        Perfil perfil = perfilService.obtenerPerfilDeUsuarioAutenticado();
        model.addAttribute("perfil", perfil);
        return "Perfil-User";
    }
    
    @PostMapping("/guardar")
    public String guardarPerfil(@Valid @ModelAttribute ("perfil") Perfil perfil,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()){
            model.addAttribute("mostrarModal", true);
            return "Perfil-User";
        }
            perfilService.guardarPerfil(perfil);

        return "redirect:/perfil";
    }
}
