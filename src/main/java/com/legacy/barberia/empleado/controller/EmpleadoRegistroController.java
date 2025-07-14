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
        prepararFormulario(model, new RegisEmpleadoDTO());
        return "staff-form-list";
    }
    
    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute("empleadoDto") RegisEmpleadoDTO dto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            prepararFormulario(model, dto);
            return "staff-form-list";
        }
        
        if (dto.getId() != null) {
            empleadoService.actualizarEmpleado(dto);
        } else {
            empleadoService.registrarEmpleado(dto);
        }
        
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
            dto.setUserId(empleado.getUsuarioEmpleado().getId());
            dto.setFechaContrato(empleado.getFechaContrato());
            dto.setFechaDespido(empleado.getFechaDespido());
            dto.setSalario(empleado.getSalario());
            dto.setEstado(empleado.getEstado());
        
        prepararFormulario(model, dto);
        return "staff-form-list";
    }
    
    
    private void prepararFormulario(Model model, RegisEmpleadoDTO dto) {
        model.addAttribute("empleadoDto", dto);
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("totalEmpleados", empleadoService.contarEmpleados());
        model.addAttribute("estados", EstadoEmpleado.values());
    }
    
}
