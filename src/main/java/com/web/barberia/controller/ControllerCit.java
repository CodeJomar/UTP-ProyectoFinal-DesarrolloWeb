package com.web.barberia.controller;

import com.web.barberia.model.Cita;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCit {
    
    List<Cita> citas = new ArrayList<>();
    
    @GetMapping("/citas")
    public String pageCitas(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas";
    }
    
    @PostMapping("/tablaCitas")
    public String saveCitas(@ModelAttribute Cita cita) {
        citas.add(cita);
        return "redirect:/tablaCitas";
    }
    
    @GetMapping("/tablaCitas")
    public String pageTablaCitas(Model model) {
        model.addAttribute("citas", citas);
        return "tablaCitas";
    }
}
