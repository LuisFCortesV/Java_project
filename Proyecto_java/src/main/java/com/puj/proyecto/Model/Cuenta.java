package com.puj.proyecto.Model;

import java.util.ArrayList;

public abstract class Cuenta {
    private long id;
    private long numero;
    ArrayList<Llamada> llamadas;

    //Metodos
    public abstract long obtener_pago_cuenta();

    //Contructor
    public Cuenta(long id, long numero, ArrayList<Llamada> llamadas) {
        this.id = id;
        this.numero = numero;
        this.llamadas = new ArrayList<>();
    }

    //Getter - Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(ArrayList<Llamada> llamadas) {
        this.llamadas = llamadas;
    }
}
