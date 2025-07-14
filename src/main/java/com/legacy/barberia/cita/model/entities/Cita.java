package com.legacy.barberia.cita.model.entities;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.cita.model.enums.EstadoCita;
import com.legacy.barberia.cita.model.enums.MotivoCita;
import com.legacy.barberia.validaciones.anotaciones.HorarioCita;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {

    @NotNull(message = "Ingrese el horario de su preferencia")
    @HorarioCita
    private LocalTime hora;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    private String observaciones;


    @NotNull(message = "Seleccione un motivo")
    @Enumerated(EnumType.STRING)
    private MotivoCita motivo;

    @NotNull(message = "El estado de la cita es obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
