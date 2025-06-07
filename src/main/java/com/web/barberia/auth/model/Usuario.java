package com.web.barberia.auth.model;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "clave", nullable = false)
    private String clave;
    
    @Column(name = "rol_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
    private Rol rol;
    
    public Usuario() {
    }
    
    public Usuario(Long id, String nombre, String email, String clave, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }
    
    public Usuario(String nombre, String email, String clave, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }
    
    public Usuario(String email) {
        this.email = email;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
