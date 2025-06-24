package com.legacy.barberia.producto.controller;

import com.legacy.barberia.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String mostrarPaginaProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos";
    }
}
