package com.puj.proyecto.Enums;

public enum Paises_disponibles {
    COLOMBIA("Colombia",57),
    ARGENTINA("Colombia",57),
    VENEZUELA("Colombia",57),
    PANAMA("Colombia",57),
    BOLIVIA("Colombia",57);

    private String nombre;
    private int extension;

    Paises_disponibles(String nombre, int extension){
        this.nombre = nombre;
        this.extension = extension;
    }

    public String getNombre() {
        return nombre;
    }

    public int getExtension() {
        return extension;
    }
}
