package com.puj.proyecto.Model;

import java.time.LocalDate;

public abstract class Llamada {
    private long duracion;
    private LocalDate fecha;
    private long telefono_destinatario;
    private long valor;

    //Metodos
    public abstract long calcular_valor();

    //Constructor
    public Llamada(long duracion, LocalDate fecha, long telefono_destinatario, long valor) {
        this.duracion = duracion;
        this.fecha = fecha;
        this.telefono_destinatario = telefono_destinatario;
        this.valor = valor;
    }

    //Getter - Setter
    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getTelefono_destinatario() {
        return telefono_destinatario;
    }

    public void setTelefono_destinatario(long telefono_destinatario) {
        this.telefono_destinatario = telefono_destinatario;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
