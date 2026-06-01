package com.puj.proyecto.Comparator;

import com.puj.proyecto.Model.Llamada;

import java.util.Comparator;

public class ComparatorLlamadaFecha implements Comparator<Llamada> {

    @Override
    public int compare(Llamada l1, Llamada l2) {
        return l1.getFecha().compareTo(l2.getFecha());
    }
}
