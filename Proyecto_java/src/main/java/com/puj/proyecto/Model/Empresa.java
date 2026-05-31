package com.puj.proyecto.Model;

import com.puj.proyecto.Excepciones.cliente_no_existe;

import java.util.ArrayList;

public class Empresa implements IEmpresa{
    private String nombre;
    ArrayList<Cliente> clientes;
    ArrayList<Cuenta> cuentas;

    //Metodos

        public void crear_cuenta(int numero_telefono, String indentificacion,String tipo_cuenta){
            Cliente cliente_actual = null;
            ArrayList<Llamada> llamadas = new ArrayList<>();
            ArrayList<Recarga> recargas = new ArrayList<>();

            for(Cliente c : clientes){
                if(c.getIdentificacion().equals(indentificacion)){
                    cliente_actual = c;
                }
                if(cliente_actual == null){
                    throw new cliente_no_existe();
                }
            }
            if(tipo_cuenta.equalsIgnoreCase("prepago")){
                Cuenta cuenta_nueva = new Prepago(IEmpresa.CONSECUTIVO,numero_telefono,llamadas,5,recargas);
                cliente_actual.setCuenta(cuenta_nueva);
                cuentas.add(cuenta_nueva);
                System.out.println("Su nueva cuenta PREPAGO ha sido creada, numero de cuenta: " + IEmpresa.CONSECUTIVO);
            }
            else{
                Cuenta cuenta_nueva = new Postpago(CONSECUTIVO,numero_telefono,llamadas,20000);
                cliente_actual.setCuenta(cuenta_nueva);
                cuentas.add(cuenta_nueva);
                System.out.println("Su nueva cuenta POSTPAGO ha sido creada, numero de cuenta: " + IEmpresa.CONSECUTIVO);

            }
        }

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

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
