package com.legacy.barberia.producto.service;

import com.legacy.barberia.producto.model.CategoriaProducto;
import com.legacy.barberia.producto.model.EstadoProducto;
import com.legacy.barberia.producto.model.Producto;
import com.legacy.barberia.producto.repository.CategoriaProductoRepository;
import com.legacy.barberia.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;
    
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
    public List<CategoriaProducto> listarCategorias() {
        return categoriaProductoRepository.findAll();
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
