package com.web.barberia.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre_rol")
    private String nombre;
    
    public Rol() {
    }
    
    public Rol(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
