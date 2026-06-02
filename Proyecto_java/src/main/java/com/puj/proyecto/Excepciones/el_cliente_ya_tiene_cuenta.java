package com.puj.proyecto.Excepciones;

public class el_cliente_ya_tiene_cuenta extends RuntimeException {
    public el_cliente_ya_tiene_cuenta() {
        super("Este cliente ya tiene una cuenta, (SOLO PUEDE TENER UNA)");
    }
}
