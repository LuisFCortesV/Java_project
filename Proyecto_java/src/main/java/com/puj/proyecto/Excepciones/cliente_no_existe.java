package com.puj.proyecto.Excepciones;

public class cliente_no_existe extends RuntimeException {
    public cliente_no_existe() {
        super("El cliente no fue encontrado");
    }
}
