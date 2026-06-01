package com.puj.proyecto.Comparator;
import com.puj.proyecto.Model.Recarga;
import java.util.Comparator;

public class ComparatorRecargaFecha implements Comparator<Recarga> {

    @Override
    public int compare(Recarga r1, Recarga r2) {
        return r1.getFecha().compareTo(r2.getFecha());
    }
}