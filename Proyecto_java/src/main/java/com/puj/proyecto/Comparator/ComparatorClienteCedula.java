package com.puj.proyecto.Comparator;

import com.puj.proyecto.Model.Cliente;

import java.util.Comparator;

public class ComparatorClienteCedula implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getIdentificacion().compareTo(c2.getIdentificacion());
    }
}
