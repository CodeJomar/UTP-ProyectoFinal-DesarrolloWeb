package com.legacy.barberia.producto.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoProducto {
    STOCK("Stock", "stock-available"),
    AGOTADO("Agotado", "stock-out");
    
    private final String displayName;
    private final String cssClass;
    
}
