package com.legacy.barberia.empleado.controller;

import com.legacy.barberia.empleado.model.entities.Empleado;
import com.legacy.barberia.empleado.model.enums.EstadoEmpleado;
import com.legacy.barberia.empleado.model.dtos.RegisEmpleadoDTO;
import com.legacy.barberia.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff-registro")
@RequiredArgsConstructor
public class EmpleadoRegistroController {
    
    private final EmpleadoService empleadoService;
    
    @GetMapping("")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleadoDto", new RegisEmpleadoDTO());
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("totalEmpleados", empleadoService.contarEmpleados());
        model.addAttribute("estados", EstadoEmpleado.values());
        return "staff-form-list";
    }
    
    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute("empleadoDto") RegisEmpleadoDTO dto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("empleados", empleadoService.listarEmpleados());
            model.addAttribute("totalEmpleados", empleadoService.contarEmpleados());
            model.addAttribute("estados", EstadoEmpleado.values());
            return "staff-form-list";
        }
        try {
            if (dto.getId() != null) {
                empleadoService.actualizarEmpleado(dto);
            } else {
                empleadoService.registrarEmpleado(dto);
            }
        } catch (IllegalArgumentException e) {
            result.rejectValue("username", null, e.getMessage());
            model.addAttribute("empleados", empleadoService.listarEmpleados());
            model.addAttribute("totalEmpleados", empleadoService.contarEmpleados());
            model.addAttribute("estados", EstadoEmpleado.values());
            return "staff-form-list";
        }
        return "redirect:/staff-registro";
    }
    
    @GetMapping("/eliminar")
    public String eliminarEmpleado(@RequestParam Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/staff-registro";
    }
    
    @GetMapping("/editar")
    public String editarEmpleado(@RequestParam Long id, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        
        RegisEmpleadoDTO dto = new RegisEmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setUsername(empleado.getUsuarioEmpleado().getUsername());
        dto.setPassword(empleado.getUsuarioEmpleado().getPassword());
        dto.setFechaContrato(empleado.getFechaContrato());
        dto.setFechaDespido(empleado.getFechaDespido());
        dto.setSalario(empleado.getSalario());
        dto.setEstado(empleado.getEstado());
        
        model.addAttribute("empleadoDto", dto);
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("estados", EstadoEmpleado.values());
        model.addAttribute("totalEmpleados", empleadoService.contarEmpleados());
        return "staff-form-list";
    }
}
