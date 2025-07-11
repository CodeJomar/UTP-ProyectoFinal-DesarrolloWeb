package com.legacy.barberia.auth.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisUserDTO {
    
    @NotBlank(message = "Este campo es obligatorio")
    @Email(message = "Este campo debe cumplir con el formato Email")
    private String username;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String password;
    
}
