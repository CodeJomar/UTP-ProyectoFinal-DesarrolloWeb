package com.legacy.barberia.producto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoriaProducto {
    CUIDADO_CAPILAR("Cuidado Capilar", "text-bg-warning"),
    BARBA("Barba", "text-bg-dark"),
    HERRAMIENTAS("Herramientas", "text-bg-info"),
    FRAGANCIAS("Fragancias", "text-bg-primary");

    private final String displayName;
    private final String cssClass;
    
}
