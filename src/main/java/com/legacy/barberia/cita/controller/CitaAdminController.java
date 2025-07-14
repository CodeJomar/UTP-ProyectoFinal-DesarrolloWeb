package com.legacy.barberia.cita.controller;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.cita.model.entities.Cita;
import com.legacy.barberia.cita.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/citas-listado")
public class CitaAdminController {
    
    @Autowired
    private CitaService citaService;
    
    @GetMapping("")
    public String verCitasAdmin(@AuthenticationPrincipal User user, Model model) {
        if(!user.getRole().getName().name().equals("ROLE_ADMIN") && !user.getRole().getName().name().equals("ROLE_EMPLOYEE")){
            return "redirect:/citas";
        }
        List<Cita> citas = citaService.obtenerCitasGeneradas();
        model.addAttribute("citas", citas);
        return "citas-list";
    }
}
