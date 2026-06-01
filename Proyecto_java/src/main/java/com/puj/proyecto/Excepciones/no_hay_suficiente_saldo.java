package com.puj.proyecto.Excepciones;

public class no_hay_suficiente_saldo extends RuntimeException {
    public no_hay_suficiente_saldo() {
        super("No hay suficiente saldo en la cuenta, haga una recarga primero");
    }
}
