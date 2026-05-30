package com.puj.proyecto.Excepciones;

public class numero_identificacion_ya_existente extends RuntimeException {
    public numero_identificacion_ya_existente() {
        super("El numero de identificacion que ingreso ya esta en nuestra base de datos, no puede ser agregado nuevamente.");
    }
}
