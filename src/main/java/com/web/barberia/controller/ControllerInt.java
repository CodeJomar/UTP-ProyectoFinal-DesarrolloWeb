package com.web.barberia.controller;

import com.web.barberia.model.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ControllerInt {
    
    @GetMapping("/ventas")
    public String pageVentas(Model model) {
        List<Producto> productos = Arrays.asList(
            new Producto("Fijador - Mate", "Skull Men", 70, 10, "Capilar"),
            new Producto("Texturizante - Mate", "Skull Men", 60, 0, "Capilar"),
            new Producto("Shampoo", "Skull Men", 80, 20, "Capilar")
        );
        model.addAttribute("productos", productos);
        return "ventas";
    }
}
