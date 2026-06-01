package com.puj.proyecto.Excepciones;

public class id_cliente_no_cumple extends RuntimeException {
    public id_cliente_no_cumple() {
        super("El id del cliente o su tipo de cuenta (Prepago) no cumple con su elección.");
    }
}
