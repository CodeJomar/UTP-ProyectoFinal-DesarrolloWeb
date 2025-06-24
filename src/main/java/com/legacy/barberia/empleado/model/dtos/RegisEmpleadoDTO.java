package com.legacy.barberia.empleado.model.dtos;

import com.legacy.barberia.empleado.model.enums.EstadoEmpleado;
import com.legacy.barberia.validaciones.anotaciones.DuracionMinimaContrato;
import com.legacy.barberia.validaciones.anotaciones.SalarioMinimo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DuracionMinimaContrato
public class RegisEmpleadoDTO {
    
    private Long id;
    
    @NotBlank(message = "Este campo es obligatorio")
    @Email(message = "Este campo debe tener el formato email")
    private String username;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String password;
    
    @NotNull(message = "Este campo es obligatorio")
    @FutureOrPresent(message = "La fecha de contrato debe ser hoy o un día futuro")
    private LocalDate fechaContrato;
    
    @NotNull(message = "Este campo es obligatorio")
    @FutureOrPresent(message = "La fecha de despido no puede ser un día pasado")
    private LocalDate fechaDespido;
    
    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El salario debe ser un número positivo")
    @SalarioMinimo
    private BigDecimal salario;
    
    @NotNull(message = "Este campo es obligatorio")
    private EstadoEmpleado estado;
    
}
