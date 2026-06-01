package com.puj.proyecto.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IEmpresa {

    public static int CONSECUTIVO = 0;
    public static final long precio_minuto = 10000;


    public static LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

    ArrayList<Cliente> getClientes();
}
