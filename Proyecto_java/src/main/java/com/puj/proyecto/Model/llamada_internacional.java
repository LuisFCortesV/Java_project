package com.puj.proyecto.Model;

import java.time.LocalDate;

public class llamada_internacional extends Llamada{
    private String pais_destino;

    //Metodos
    public long calcular_valor(){
        return 0;
    }

    //Constructor
    public llamada_internacional(long duracion, LocalDate fecha, long telefono_destinatario, long valor, String pais_destino) {
        super(duracion, fecha, telefono_destinatario, valor);
        this.pais_destino = pais_destino;
    }

    //Getter - Setter
    public String getPais_destino() {
        return pais_destino;
    }

    public void setPais_destino(String pais_destino) {
        this.pais_destino = pais_destino;
    }
}
