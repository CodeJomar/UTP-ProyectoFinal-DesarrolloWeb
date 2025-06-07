package com.web.barberia.model;

public class Producto {
    private String nombre;
    private String marca;
    private double precio;
    private int descuento;
    private String categoria;
    
    public Producto(String nombre, String marca, double precio, int descuento, String categoria) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.descuento = descuento;
        this.categoria = categoria;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public int getDescuento() {
        return descuento;
    }
    
    public String getCategoria() {
        return categoria;
    }
}
