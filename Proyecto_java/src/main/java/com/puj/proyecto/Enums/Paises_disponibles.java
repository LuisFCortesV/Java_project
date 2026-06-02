package com.puj.proyecto.Enums;

public enum Paises_disponibles {
    ARGENTINA("Colombia",54),
    VENEZUELA("Colombia",55),
    PANAMA("Colombia",51),
    BOLIVIA("Colombia",58);

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
