package com.puj.proyecto.Model;

import com.puj.proyecto.Excepciones.cliente_no_existe;
import com.puj.proyecto.Excepciones.id_cliente_no_cumple;
import com.puj.proyecto.Excepciones.no_hay_suficiente_saldo;

import java.time.LocalDate;
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
            }
            if(cliente_actual == null){
                throw new cliente_no_existe();
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

        public void registrar_llamada(int id, String tipo_llamada, String pais, LocalDate fecha, int telefono_destinatario, int duracion_llamada){
            Cuenta cuenta_actual = null;
            for(Cuenta c : cuentas){
                if(c.getId() == id){
                    cuenta_actual = c;
                }
            }
            if(cuenta_actual == null){
                throw new cliente_no_existe();
            }

            if(tipo_llamada.equalsIgnoreCase("nacional")){
                Llamada ll_nueva = new Llamada_nacional(duracion_llamada,fecha,telefono_destinatario,0);
                ll_nueva.setValor(ll_nueva.calcular_valor());

                if(cuenta_actual instanceof Prepago){
                    int mes = IEmpresa.obtenerFechaActual().getMonthValue();
                    long valor_total_recargas = 0;
                    long valor_llamadas_hechas = 0;

                    Prepago prepago = (Prepago) cuenta_actual;

                    for(Recarga r : prepago.getRecargas()){
                        int mes_recarga = r.getFecha().getMonthValue();
                        if(mes_recarga == mes){

                            valor_total_recargas += r.getValor();
                        }
                    }

                    for(Llamada l : prepago.getLlamadas()){
                        if(l.getFecha().getMonthValue() == mes){
                            valor_llamadas_hechas += l.getValor();
                        }
                    }

                    long saldo_disponible = valor_total_recargas - valor_llamadas_hechas;


                    if(ll_nueva.getValor() > saldo_disponible){
                        throw new no_hay_suficiente_saldo();
                    }
                    else{
                        cuenta_actual.getLlamadas().add(ll_nueva);
                        System.out.println("Llamada registrada exitosamente");
                    }
                }
                else if(cuenta_actual instanceof Postpago){
                    ll_nueva.setValor(0);
                    cuenta_actual.getLlamadas().add(ll_nueva);
                    System.out.println("Llamada registrada existosamente");
                }
            }
            else{
                Llamada ll_inter_nueva = new llamada_internacional(duracion_llamada,fecha,telefono_destinatario,0,pais);
                ll_inter_nueva.setValor(ll_inter_nueva.calcular_valor());

                if(cuenta_actual instanceof Prepago){
                    int mes = IEmpresa.obtenerFechaActual().getMonthValue();
                    long valor_total_recargas = 0;
                    long valor_llamadas_hechas = 0;

                    Prepago prepago = (Prepago) cuenta_actual;

                    for(Recarga r : prepago.getRecargas()){
                        int mes_recarga = r.getFecha().getMonthValue();
                        if(mes_recarga == mes){

                            valor_total_recargas += r.getValor();
                        }
                    }

                    for(Llamada l : prepago.getLlamadas()){
                        if(l.getFecha().getMonthValue() == mes){
                            valor_llamadas_hechas += l.getValor();
                        }
                    }

                    long saldo_disponible = valor_total_recargas - valor_llamadas_hechas;


                    if(ll_inter_nueva.getValor() > saldo_disponible){
                        throw new no_hay_suficiente_saldo();
                    }
                    else{
                        cuenta_actual.getLlamadas().add(ll_inter_nueva);
                        System.out.println("Llamada registrada exitosamente");
                    }
                }else if(cuenta_actual instanceof Postpago){
                    ll_inter_nueva.setValor(ll_inter_nueva.calcular_valor());
                    cuenta_actual.getLlamadas().add(ll_inter_nueva);
                    System.out.println("Llamada registrada existosamente");
                }
            }
        }

        public void agregar_recarga(long id, LocalDate fecha_recarga, long valor){
            Cuenta cuenta_actual = null;
            for(Cuenta c : cuentas){
                if(c.getId() == id && c instanceof Prepago){
                    cuenta_actual = c;
                    break;
                }
            }

            if(cuenta_actual == null){
                throw new id_cliente_no_cumple();
            }

                Prepago prepago = (Prepago) cuenta_actual;

                Recarga nueva_recarga = new Recarga(fecha_recarga,valor);
                prepago.getRecargas().add(nueva_recarga);
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
