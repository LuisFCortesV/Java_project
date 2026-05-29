package com.puj.proyecto.Model;

import java.util.ArrayList;

public class Empresa {
    private String nombre;
    ArrayList<Cliente> clientes;
    ArrayList<Cuenta> cuentas;

    //Metodos


    //Constructor
    public Empresa(String nombre, ArrayList<Cliente> clientes) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    //Getter - Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
}
