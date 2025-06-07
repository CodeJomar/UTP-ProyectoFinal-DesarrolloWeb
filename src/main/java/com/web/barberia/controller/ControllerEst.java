package com.web.barberia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerEst {
    
    @GetMapping("/prueba3") // cómo se va a acceder a esta web mediante el url
    private String pageHTML() {
        return "prueba3"; //nombre del html (sin el .html)
    }
    
    @GetMapping("/index")
    private String pageIndex() {
        return "index";
    }
    
    @GetMapping("/carrito")
    private String pageCarrito() {
        return "carrito";
    }
    /*
    @GetMapping("/citas")
    private String pageCitas() {
        return "citas";
    }
    */
    /*
    @GetMapping("/login")
    private String pageLogin() {
        return "login";
    }
    */
    /*
    @GetMapping("/tablaCitas")
    private String pageTablaCitas() {
        return "tablaCitas";
    }
     */
    /*
    @GetMapping("/tablaUsuarios")
    private String pageTablaUsuarios() {
        return "tablaUsuarios";
    }
    */
    @GetMapping("/trabajadores")
    private String pageTrabajadores() {
        return "trabajadores";
    }
    /*
    @GetMapping("/ventas")
    private String pageVentas() {
        return "ventas";
    }
    */
    @GetMapping("/prueba")
    private String pagePrueba() {
        return "prueba";
    }
}
