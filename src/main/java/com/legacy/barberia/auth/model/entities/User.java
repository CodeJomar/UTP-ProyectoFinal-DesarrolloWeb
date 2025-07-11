package com.legacy.barberia.auth.model.entities;

import com.legacy.barberia.perfil.model.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(unique = true, nullable = false)
    private String username; // representa el email del usuario
    
    @Column(nullable = false)
    private String password;
    
    // Si tienes una relación bidireccional con Perfil, agrégala aquí
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Excluir de toString para evitar referencia circular
    private Perfil perfil;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
    
    /* ======= Estos son para convertir mi Entidad (User) a un UserDetails ======= */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName().name()));
    }
    
    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
}
