package com.legacy.barberia.empleado.model.entities;

import com.legacy.barberia.auth.model.entities.User;
import com.legacy.barberia.empleado.model.enums.EstadoEmpleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_contrato", nullable = false)
    private LocalDate fechaContrato;
    
    @Column(name = "fecha_despido", nullable = false)
    private LocalDate fechaDespido;
    
    @Column(nullable = false)
    private BigDecimal salario;
    
    @Enumerated(EnumType.STRING)
    private EstadoEmpleado estado;
    
    @OneToOne
    @JoinColumn(name = "usuario_empleado", referencedColumnName = "id", nullable = false, unique = true)
    private User usuarioEmpleado;
    
}
