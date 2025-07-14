package com.legacy.barberia.cita.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CitaDTO {
    private Long id;

    private String userEmail;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    private String observaciones;

    @NotBlank(message = "Es importante seleccionar un motivo")
    private String motivo;

    @NotBlank(message = "Seleccione un estado")
    private String estado;


}


