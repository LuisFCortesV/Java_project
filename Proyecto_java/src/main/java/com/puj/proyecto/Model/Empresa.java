package com.puj.proyecto.Model;

import com.puj.proyecto.App.Utils;
import com.puj.proyecto.Comparator.ComparatorClienteCedula;
import com.puj.proyecto.Comparator.ComparatorLlamadaFecha;
import com.puj.proyecto.Comparator.ComparatorRecargaFecha;
import com.puj.proyecto.Enums.Paises_disponibles;
import com.puj.proyecto.Excepciones.cliente_no_existe;
import com.puj.proyecto.Excepciones.el_cliente_ya_tiene_cuenta;
import com.puj.proyecto.Excepciones.id_cliente_no_cumple;
import com.puj.proyecto.Excepciones.no_hay_suficiente_saldo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Empresa implements IEmpresa, Serializable {
    private String nombre;
    ArrayList<Cliente> clientes;
    ArrayList<Cuenta> cuentas;

    //Metodos

        public void crear_cuenta(long numero_telefono, String indentificacion,String tipo_cuenta) throws Exception {
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

            if(cliente_actual.getCuenta() != null){
                throw new el_cliente_ya_tiene_cuenta();
            }


            if(tipo_cuenta.equalsIgnoreCase("prepago")){
                Cuenta cuenta_nueva = new Prepago(Utils.CONSECUTIVO++,numero_telefono,llamadas,5,recargas);
                cliente_actual.setCuenta(cuenta_nueva);
                cuentas.add(cuenta_nueva);

                Recarga recarga_inicial = new Recarga(IEmpresa.obtenerFechaActual(), 5 * Utils.precio_minuto);
                ((Prepago) cuenta_nueva).getRecargas().add(recarga_inicial);
                System.out.println("Su nueva cuenta PREPAGO ha sido creada, numero de cuenta: " + (Utils.CONSECUTIVO - 1) + "\n" + "Ademas tienes una recarga de 5 minutos.");
            }
            else if(tipo_cuenta.equalsIgnoreCase("postpago")){
                Cuenta cuenta_nueva = new Postpago(Utils.CONSECUTIVO++,numero_telefono,llamadas,20000);
                cliente_actual.setCuenta(cuenta_nueva);
                cuentas.add(cuenta_nueva);
                System.out.println("Su nueva cuenta POSTPAGO ha sido creada, numero de cuenta: " + (Utils.CONSECUTIVO - 1));
            }
            else{
                throw new Exception("Tipo de cuenta no valido");
            }
        }

        public void registrar_llamada(int id, String tipo_llamada, String pais, LocalDate fecha, long telefono_destinatario, int duracion_llamada){
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
                Paises_disponibles indicativo = Paises_disponibles.valueOf(pais.toUpperCase());
                long telefono_completo = Long.parseLong(indicativo.getExtension() + String.valueOf(telefono_destinatario));

                Llamada ll_inter_nueva = new llamada_internacional(duracion_llamada,fecha,telefono_completo,0,pais,indicativo);
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
        // nuevos metodos

    @Override
    public String reporteFacturacionPostpago(String identificacion, int anio, int mes) {
        String reporte = "";
        boolean encontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                encontrado = true;
                Cuenta cuenta = cliente.getCuenta();
                if (cuenta instanceof Postpago) {
                    Postpago post = (Postpago) cuenta;
                    reporte += "===== CLIENTE =====\n";
                    reporte += "Nombre: "
                            + cliente.getNombre()
                            + "\n";
                    reporte += "Identificacion: "
                            + cliente.getIdentificacion()
                            + "\n";
                    reporte += "Direccion: "
                            + cliente.getDireccion()
                            + "\n\n";
                    reporte += "===== CUENTA POSTPAGO =====\n";
                    reporte += "ID: "
                            + post.getId()
                            + "\n";
                    reporte += "Numero: "
                            + post.getNumero()
                            + "\n";
                    reporte += "Cargo fijo: "
                            + post.getCargo_fijo()
                            + "\n\n";
                    Collections.sort(post.getLlamadas(), new ComparatorLlamadaFecha());
                    long totalMinutos = 0;
                    long totalValor = 0;
                    reporte += "===== LLAMADAS =====\n";
                    for (Llamada llamada : post.getLlamadas()) {
                        if (llamada.getFecha().getYear() == anio && llamada.getFecha().getMonthValue() == mes) {
                            reporte += llamada + "\n";
                            totalMinutos += llamada.getDuracion();
                            totalValor += llamada.getValor();
                        }
                    }
                    reporte += "\nTotal minutos: "
                            + totalMinutos
                            + "\n";

                    reporte += "Total valor llamadas: "
                            + totalValor
                            + "\n";

                    reporte += "Total a pagar: "
                            + post.obtener_pago_cuenta(
                            anio,
                            mes)
                            + "\n";
                }
                else {
                    reporte += "El cliente no tiene cuenta postpago";
                }
            }
        }
        if (!encontrado) {
            reporte = "No existe un cliente con esa identificacion";
        }
        return reporte;
    }

    @Override
    public String reporteRecargas(int anio, int mes) {
        String reporte = "";
        long totalRecargasSistema = 0;
        long totalMinutosSistema = 0;
        Collections.sort(clientes, new ComparatorClienteCedula());
        for (Cliente cliente : clientes) {
            Cuenta cuenta = cliente.getCuenta();
            if (cuenta instanceof Prepago) {
                Prepago prep = (Prepago) cuenta;
                reporte += "\n====================\n";
                reporte += "Nombre: "
                        + cliente.getNombre()
                        + "\n";
                reporte += "Identificacion: "
                        + cliente.getIdentificacion()
                        + "\n";
                reporte += "Direccion: "
                        + cliente.getDireccion()
                        + "\n";
                reporte += "\nCUENTA PREPAGO\n";
                reporte += "ID: "
                        + prep.getId()
                        + "\n";
                reporte += "Numero: "
                        + prep.getNumero()
                        + "\n";
                long totalRecargasCliente = 0;
                long totalMinutosCliente = 0;
                long totalValorLlamadas = 0;
                Collections.sort(prep.getLlamadas(), new ComparatorLlamadaFecha());
                reporte += "\nLLAMADAS\n";
                for (Llamada llamada : prep.getLlamadas()) {
                    if (llamada.getFecha().getYear() == anio && llamada.getFecha().getMonthValue() == mes) {
                        reporte += llamada + "\n";
                        totalMinutosCliente += llamada.getDuracion();
                        totalValorLlamadas += llamada.getValor();
                    }
                }
                reporte += "Total minutos: "
                        + totalMinutosCliente
                        + "\n";
                reporte += "Total valor llamadas: "
                        + totalValorLlamadas
                        + "\n";
                Collections.sort(prep.getRecargas(), new ComparatorRecargaFecha());
                reporte += "\nRECARGAS\n";
                for (Recarga r : prep.getRecargas()) {
                    if (r.getFecha().getYear() == anio && r.getFecha().getMonthValue() == mes) {
                        reporte += "Fecha: " + r.getFecha()
                                + " Valor: "
                                + r.getValor()
                                + "\n";
                        totalRecargasCliente += r.getValor();
                    }
                }
                reporte += "Total recargas: "
                        + totalRecargasCliente
                        + "\n";
                reporte += "Total minutos cliente: "
                        + totalMinutosCliente
                        + "\n";
                totalRecargasSistema += totalRecargasCliente;
                totalMinutosSistema += totalMinutosCliente;
            }
        }
        reporte += "\n====================\n";

        reporte += "TOTAL RECARGAS SISTEMA: " + totalRecargasSistema + "\n";
        reporte += "TOTAL MINUTOS SISTEMA: "
                + totalMinutosSistema + "\n";
        return reporte;
    }


    //Constructor
    public Empresa(String nombre) {
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
