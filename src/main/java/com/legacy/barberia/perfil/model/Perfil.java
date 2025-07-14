package com.legacy.barberia.perfil.model;


import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.validaciones.anotaciones.FormatoCelular;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfiles")
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max=15, message = "El nombre debe tener entre 3 y 15 caracteres")
    @Column(name = "nombre_usuario", length = 15)
    private String nombre;

    @FormatoCelular
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size (max = 40, message = "La dirección supera el número de caracteres")
    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "direccion", length = 100)
    private String direccion;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ToString.Exclude
    private User user;
}
