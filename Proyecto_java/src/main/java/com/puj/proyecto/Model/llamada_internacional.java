package com.puj.proyecto.Model;

import java.time.LocalDate;

public class llamada_internacional extends Llamada{
    private String pais_destino;

    //Constructor
    public llamada_internacional(long duracion, LocalDate fecha, long telefono_destinatario, long valor, String pais_destino) {
        super(duracion, fecha, telefono_destinatario, valor);
        this.pais_destino = pais_destino;
    }

    //Metodos
    public long calcular_valor(){
        return (long) ((getDuracion() * IEmpresa.precio_minuto) + 0.2 * getDuracion() * IEmpresa.precio_minuto);
    }

    //Getter - Setter
    public String getPais_destino() {
        return pais_destino;
    }

    public void setPais_destino(String pais_destino) {
        this.pais_destino = pais_destino;
    }
}
