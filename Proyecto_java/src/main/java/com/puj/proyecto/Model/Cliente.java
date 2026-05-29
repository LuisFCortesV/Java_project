package com.puj.proyecto.Model;

public class Cliente {
    private String direccion;
    private String identificacion;
    private String nombre;
    private String tipo_id;
    private Cuenta cuenta;

    // Constructor
    public Cliente(String direccion, String identificacion, String nombre, String tipo_id) {
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipo_id = tipo_id;
    }

    // Getter - Setter
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
}
