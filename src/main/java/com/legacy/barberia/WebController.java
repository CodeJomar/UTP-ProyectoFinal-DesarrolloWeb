package com.legacy.barberia;

import com.legacy.barberia.auth.model.dtos.RegisUserDTO;
import com.legacy.barberia.empleado.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("userDTO")) {
            model.addAttribute("userDTO", new RegisUserDTO());
        }
        return "index";
    }
    
    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
    
    @GetMapping("/perfil")
    public String perfilUser() {
        return "Perfil-User";
    }


//    @GetMapping("/citas")
//    public String mostrarPaginaCitas() {
//        return "citas-form-list";
//    }
    
    @GetMapping("/citas-listado")
    public String mostrarPaginaCitasRegistradas() {
        return "citas-list";
    }
    
//    @GetMapping("/productos")
//    public String mostrarPaginaProductos() {
//        return "productos";
//    }
//
//    @GetMapping("/productos-registro")
//    public String mostrarPaginaProductosRegistrados() {
//        return "productos-form-list";
//    }
    
}
