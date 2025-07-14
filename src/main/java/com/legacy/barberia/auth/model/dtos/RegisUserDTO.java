package com.legacy.barberia.auth.model.dtos;

import com.legacy.barberia.auth.model.entities.Role;
import com.legacy.barberia.perfil.model.Perfil;
import com.legacy.barberia.validaciones.anotaciones.EmailUnico;
import com.legacy.barberia.validaciones.anotaciones.PasswordFuerte;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EmailUnico
public class RegisUserDTO {

    private String id;
    
    @NotBlank(message = "Este campo es obligatorio")
    @Email(message = "Este campo debe cumplir con el formato Email")
    private String username;
    
    @NotBlank(message = "Este campo es obligatorio")
    @PasswordFuerte
    private String password;
    
    private Perfil perfil;
    
    private Role role;
}
