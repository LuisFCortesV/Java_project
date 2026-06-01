package com.puj.proyecto.Model;

import java.time.LocalDate;

public class Llamada_nacional extends Llamada {

    //Metodos
    public long calcular_valor(){
        return getDuracion() * IEmpresa.precio_minuto;
    }

    // Constructor
    public Llamada_nacional(long duracion, LocalDate fecha, long telefono_destinatario, long valor) {
        super(duracion, fecha, telefono_destinatario, valor);
    }
}
