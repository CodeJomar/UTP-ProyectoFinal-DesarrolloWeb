package com.legacy.barberia.empleado.controller;

import com.legacy.barberia.empleado.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class EmpleadoController {
    
    private final EmpleadoService empleadoService;
    
    @GetMapping("")
    public String mostrarPaginaEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        return "staff";
    }
}
