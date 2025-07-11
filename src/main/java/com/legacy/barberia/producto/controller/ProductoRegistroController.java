package com.legacy.barberia.producto.controller;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.auth.repository.UserRepository;
import com.legacy.barberia.producto.model.CategoriaProducto;
import com.legacy.barberia.producto.model.EstadoProducto;
import com.legacy.barberia.producto.model.Producto;
import com.legacy.barberia.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos-registro")
@RequiredArgsConstructor
public class ProductoRegistroController {
    
    private final ProductoService productoService;
    private final UserRepository userRepository;
    
    @GetMapping("")
    public String mostrarPaginaProductosRegistrados(Model model) {
        Producto producto = new Producto();
        producto.setEstado(EstadoProducto.STOCK);
        producto.setCategoria(CategoriaProducto.CUIDADO_CAPILAR);
        
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("totalProductos", productoService.contarCantidadProductos());
        model.addAttribute("categorias", CategoriaProducto.values());
        return "productos-form-list";
    }
    
    @PostMapping("/guardarProducto")
    public String guardarProductos(@Valid @ModelAttribute Producto producto,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User creador = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            producto.setCreador(creador);
            
            // Calcular estado según stock
            if (producto.getStock() == 0) {
                producto.setEstado(EstadoProducto.AGOTADO);
            } else {
                producto.setEstado(EstadoProducto.STOCK);
            }
            
            if (result.hasErrors()) {
                model.addAttribute("productos", productoService.listarProductos());
                model.addAttribute("totalProductos", productoService.contarCantidadProductos());
                model.addAttribute("categorias", CategoriaProducto.values());
                return "productos-form-list";
            }
            
            productoService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("success", "Producto guardado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el producto... Rellene los campos vacíos");
        }
        
        return "redirect:/productos-registro";
    }
    
    @GetMapping("/eliminarProducto")
    public String eliminarProductos(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminarProducto(id);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto");
        }
        return "redirect:/productos-registro";
    }
    
    @GetMapping("/editarProducto")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Producto producto = productoService.obtenerProductorPorId(id);
            if (producto == null) {
                redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
                return "redirect:/productos-registro";
            }
            model.addAttribute("producto", producto);
            model.addAttribute("productos", productoService.listarProductos());
            model.addAttribute("totalProductos", productoService.contarCantidadProductos());
            model.addAttribute("categorias", CategoriaProducto.values());
            model.addAttribute("editando", true);
            return "productos-form-list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cargar el producto");
            return "redirect:/productos-registro";
        }
    }
}
