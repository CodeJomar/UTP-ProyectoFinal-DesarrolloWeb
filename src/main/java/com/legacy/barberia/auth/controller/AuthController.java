package com.legacy.barberia.auth.controller;

import com.legacy.barberia.auth.model.dtos.RegisUserDTO;
import com.legacy.barberia.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    
    @PostMapping("/registro")
    public String procesoRegistro(@ModelAttribute("userDTO") @Valid RegisUserDTO dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", result);
            redirectAttributes.addFlashAttribute("userDTO", dto);
            return "redirect:/?errorRegistro";
        }
        userService.registrarUsuario(dto);
        return "redirect:/?registrado";
    }
}
