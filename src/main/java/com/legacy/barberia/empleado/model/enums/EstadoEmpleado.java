package com.legacy.barberia.empleado.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoEmpleado {
    ACTIVO("Activo", "bg-success"),
    INACTIVO("Inactivo", "bg-danger");
    
    private final String displayName;
    private final String cssClass;
    
}
