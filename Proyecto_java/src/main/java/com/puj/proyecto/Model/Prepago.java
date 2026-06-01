package com.puj.proyecto.Model;

import java.util.ArrayList;

public class Prepago extends Cuenta{
    private long numero_minutos;
    ArrayList<Recarga> recargas;

    //Metodos
    public long obtener_pago_cuenta(int anio, int mes) {
        long total = 0;
        for(Recarga r : recargas){
            if(r.getFecha().getYear() == anio && r.getFecha().getMonthValue() == mes){
                total += r.getValor();
            }
        }
        return total;
    }

    //Constructor
    public Prepago(long id, long numero, ArrayList<Llamada> llamadas, long numero_minutos, ArrayList<Recarga> recargas) {
        super(id, numero, llamadas);
        this.numero_minutos = numero_minutos;
        this.recargas = new ArrayList<>();
    }

    //Getter - Setter

    public long getNumero_minutos() {
        return numero_minutos;
    }

    public void setNumero_minutos(long numero_minutos) {
        this.numero_minutos = numero_minutos;
    }

    public ArrayList<Recarga> getRecargas() {
        return recargas;
    }

    public void setRecargas(ArrayList<Recarga> recargas) {
        this.recargas = recargas;
    }
}
