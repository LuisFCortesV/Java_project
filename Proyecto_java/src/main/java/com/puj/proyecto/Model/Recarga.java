package com.puj.proyecto.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Recarga implements Serializable {
    private LocalDate fecha;
    private long valor;

    //Constructor
    public Recarga(LocalDate fecha, long valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    //Getter - Setter
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
