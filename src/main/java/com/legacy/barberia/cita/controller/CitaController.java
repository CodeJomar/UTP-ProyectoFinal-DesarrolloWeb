package com.legacy.barberia.cita.controller;

import com.legacy.barberia.cita.model.Cita;
import com.legacy.barberia.cita.model.EstadoCita;
import com.legacy.barberia.cita.model.MotivoCita;
import com.legacy.barberia.cita.service.CitaService;
import com.legacy.barberia.usuario.model.Usuario;
import com.legacy.barberia.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    public String verCitas(Model model){
        Long usuarioId= 1L;  //Simulación de usuario
        model.addAttribute("cita", new Cita());
        model.addAttribute("citas", citaService.listarPorUsuario(usuarioId));
        model.addAttribute("motivos", MotivoCita.values());
        model.addAttribute("estados", EstadoCita.values());

        return "citas-form-list";
    }

    @PostMapping("/guardar")
    public String guardarCita (@Valid @ModelAttribute("cita") Cita cita,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("citas", citaService.listarPorUsuario(1L));
            model.addAttribute("motivos", MotivoCita.values());
            model.addAttribute("estados", EstadoCita.values());
            model.addAttribute("abrirModal", true);
            return "/citas-form-list"; // volver al formulario con errores
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(1L);
        if (usuarioOpt.isEmpty()) return "redirect:/error";

        cita.setUsuario(usuarioOpt.get());
        citaService.guardarCita(cita);
        redirectAttributes.addFlashAttribute("success", "Cita registrada correctamente.");
        return "redirect:/citas";
    }
    
    @PostMapping("/eliminar")
    public String eliminarCita(@RequestParam Long id){
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }
}
