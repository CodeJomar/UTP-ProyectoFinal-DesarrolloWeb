package com.legacy.barberia.producto.controller;

import com.legacy.barberia.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductosController {
    
    private final ProductoService productoService;
    
    @GetMapping("")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos";
    }
}

