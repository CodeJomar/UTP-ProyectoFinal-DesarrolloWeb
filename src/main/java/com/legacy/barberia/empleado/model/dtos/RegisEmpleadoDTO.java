package com.legacy.barberia.empleado.model.dtos;

import com.legacy.barberia.empleado.model.enums.EstadoEmpleado;
import com.legacy.barberia.validaciones.anotaciones.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DuracionMinimaContrato
@EmailUnicoEmpleado
public class RegisEmpleadoDTO {
    
    private Long id;
    
    private String userId;
    
    @NotBlank(message = "Este campo es obligatorio")
    @Email(message = "Este campo debe tener el formato email")
    private String username;
    
    @PasswordFuerte
    private String password;
    
    @NotNull(message = "Este campo es obligatorio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaContrato;
    
    @NotNull(message = "Este campo es obligatorio")
    @FutureOrPresent(message = "La fecha de despido no puede ser un día pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDespido;
    
    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El salario debe ser un número positivo")
    @SalarioMinimo
    private BigDecimal salario;
    
    @NotNull(message = "Este campo es obligatorio")
    private EstadoEmpleado estado;
}

