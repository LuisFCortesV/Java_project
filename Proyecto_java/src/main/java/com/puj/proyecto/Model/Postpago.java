package com.puj.proyecto.Model;

import java.util.ArrayList;

public class Postpago extends Cuenta{
    private long cargo_fijo;

    //Metodos
    public long obtener_pago_cuenta(){
        return 0;
    }

    //Constructor
    public Postpago(long id, long numero, ArrayList<Llamada> llamadas, long cargo_fijo) {
        super(id, numero, llamadas);
        this.cargo_fijo = cargo_fijo;
    }

    //Getter - Setter
    public long getCargo_fijo() {
        return cargo_fijo;
    }

    public void setCargo_fijo(long cargo_fijo) {
        this.cargo_fijo = cargo_fijo;
    }
}
