package com.legacy.barberia.cita.controller;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.cita.model.entities.Cita;
import com.legacy.barberia.cita.model.enums.EstadoCita;
import com.legacy.barberia.cita.model.enums.MotivoCita;
import com.legacy.barberia.cita.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String verCitas(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("cita", new Cita());
        boolean esAdmin = user.getRole() != null && "ADMIN".equals(user.getRole().getName().name());
        model.addAttribute("citas", esAdmin ? citaService.listarCitas() : citaService.listarPorUsuario(user));
        model.addAttribute("motivos", MotivoCita.values());
        model.addAttribute("estados", EstadoCita.values());
        return "/citas-form-list";
    }

    @PostMapping("/guardar")
    public String guardarCita (@Valid @ModelAttribute("cita") Cita cita,
                               BindingResult result,
                               @AuthenticationPrincipal User user,
                               Model model,
                               RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("citas", citaService.listarPorUsuario(user));
            model.addAttribute("motivos", MotivoCita.values());
            model.addAttribute("estados", EstadoCita.values());
            model.addAttribute("abrirModal", true);
            return "/citas-form-list";
        }

        cita.setUser(user);
        citaService.guardarCita(cita);
        redirectAttributes.addFlashAttribute("success", "Cita registrada correctamente.");
        return "redirect:/citas";
    }

    @PostMapping("/eliminar")
    public String eliminarCita(@RequestParam Long id){
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }

    @GetMapping("/estado")
    public String cambiarEstado(@RequestParam Long id,
                                @RequestParam EstadoCita nuevoEstado,
                                RedirectAttributes redirectAttributes){
        Optional<Cita> citaOpt = citaService.buscarPorId(id);
        if (citaOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cita no encontrada.");
            return "redirect:/citas";
        }

        Cita cita = citaOpt.get();
        cita.setEstado(nuevoEstado);
        citaService.guardarCita(cita);

        redirectAttributes.addFlashAttribute("success", "Estado actualizado a " + nuevoEstado);
        return "redirect:/citas";
    }

}
