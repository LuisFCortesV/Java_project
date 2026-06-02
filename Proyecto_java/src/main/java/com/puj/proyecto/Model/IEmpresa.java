package com.puj.proyecto.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public interface IEmpresa {

    public static LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }
    ArrayList<Cliente> getClientes();

    void crear_cuenta(long numero_telefono, String indentificacion,String tipo_cuenta) throws Exception;

    void agregar_recarga(long id, LocalDate fecha_recarga, long valor);

    void registrar_llamada(int id, String tipo_llamada, String pais, LocalDate fecha, long telefono_destinatario, int duracion_llamada);

    String reporteFacturacionPostpago(String identificacion, int anio, int mes);

    String reporteRecargas(int anio, int mes);

    public static LocalDate convertirTextoAFecha(String texto, String formato) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return LocalDate.parse(texto, formatter);
    }
}
