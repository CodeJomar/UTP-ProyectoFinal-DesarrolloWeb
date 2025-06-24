package com.legacy.barberia.producto.controller;

import com.legacy.barberia.producto.model.EstadoProducto;
import com.legacy.barberia.producto.model.Producto;
import com.legacy.barberia.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos-registro")
public class ProductoRegistroController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String mostrarPaginaProductosRegistrados(Model model) {
        // Crear un producto
        model.addAttribute("producto", new Producto());
        // Listar los productos existentes
        model.addAttribute("productos", productoService.listarProductos());
        // Contar todos los productos
        model.addAttribute("totalProductos", productoService.contarCantidadProductos());
        // mostrar las categorias
        model.addAttribute("categorias", productoService.listarCategorias());
        // Mostrar los estados
        model.addAttribute("estados", EstadoProducto.values());
        // Mostrar la página
        return "productos-form-list";
    }
    
    @PostMapping("/guardarProducto")
    public String guardarProductos(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos-registro";
    }
    
    @GetMapping("/eliminarProducto")
    public String eliminarProductos(@RequestParam Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos-registro";
    }
    
    @GetMapping("/editarProducto")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Producto producto = productoService.obtenerProductorPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoService.listarProductos());
        // Contar todos los productos
        model.addAttribute("totalProductos", productoService.contarCantidadProductos());
        // mostrar las categorias
        model.addAttribute("categorias", productoService.listarCategorias());
        // Mostrar los estados
        model.addAttribute("estados", EstadoProducto.values());
        return "productos-form-list";
    }
}
