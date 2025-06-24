package com.legacy.barberia.producto.model;

public enum EstadoProducto {
    STOCK("Stock", "stock-available"),
    AGOTADO("Agotado", "stock-out");
    
    private final String displayName;
    private final String cssClass;
    
    EstadoProducto(String displayName, String cssClass) {
        this.displayName = displayName;
        this.cssClass = cssClass;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getCssClass() {
        return cssClass;
    }
}
