package com.legacy.barberia.producto.service;

import com.legacy.barberia.producto.model.Producto;
import com.legacy.barberia.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }
    
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    
    public Producto obtenerProductorPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    public long contarCantidadProductos() {
        return productoRepository.count();
    }
    
}
