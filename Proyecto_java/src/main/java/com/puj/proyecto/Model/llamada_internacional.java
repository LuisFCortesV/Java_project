package com.puj.proyecto.Model;

import com.puj.proyecto.App.Utils;
import com.puj.proyecto.Enums.Paises_disponibles;

import java.time.LocalDate;

public class llamada_internacional extends Llamada{
    private String pais_destino;
    private Paises_disponibles indicativo;

    //Constructor
    public llamada_internacional(long duracion, LocalDate fecha, long telefono_destinatario, long valor, String pais_destino, Paises_disponibles indicativo) {
        super(duracion, fecha, telefono_destinatario, valor);
        this.pais_destino = pais_destino;
        this.indicativo = indicativo;
    }

    //Metodos
    public long calcular_valor(){
        return (long) ((getDuracion() * Utils.precio_minuto) + 0.2 * getDuracion() * Utils.precio_minuto);
    }

    //Getter - Setter

    public String getPais_destino() {
        return pais_destino;
    }

    public void setPais_destino(String pais_destino) {
        this.pais_destino = pais_destino;
    }

    public Paises_disponibles getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(Paises_disponibles indicativo) {
        this.indicativo = indicativo;
    }
}
