package com.puj.proyecto.App;

import com.puj.proyecto.Enums.Paises_disponibles;
import com.puj.proyecto.Model.Empresa;
import com.puj.proyecto.Model.IEmpresa;
import com.puj.proyecto.Persistencia.Manejo_archivos;

import java.time.LocalDate;
import java.util.Scanner;

public class TestConsola
{
    public static void main( String[] args )
    {

        Scanner sc = new Scanner(System.in);
        IEmpresa sistema_comunicacion = new Empresa("Javemovil");
        int opcion = 120;

        do{
            System.out.println("--------- JAVEMOVIL --------");
            System.out.println("1. Cargar clientes");
            System.out.println("2. Agregar cuenta de prepago o postpago");
            System.out.println("3. Registrar una llamada");
            System.out.println("4. Agregar una recarga");
            System.out.println("5. Reporte de facturación postpago a fin de mes");
            System.out.println("6. Reporte de recargas a fin de mes");
            System.out.println("7. Guardar datos del sistema");
            System.out.println("8. Cargar datos del sistema");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    int opcion_interna = 0;

                    System.out.println("1) Seleccionar archivo de clientes.");
                    System.out.println("2) Regresar al menú de servicios.");
                    opcion_interna = sc.nextInt();
                    sc.nextLine();

                    if(opcion_interna == 1){
                            String localizacion_archivo;

                            System.out.println("Escriba en donde se encuentra alojado el archivo");
                            localizacion_archivo = sc.nextLine();
                            sc.nextLine();

                             Manejo_archivos.cargar_clientes(sistema_comunicacion,localizacion_archivo);
                    }else{
                        break;
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Escriba su numero de identificacion asociado a la cuenta");
                        String identificacion = sc.nextLine();
                        System.out.println("Escriba un numero de telefono para asociar a la cuenta");
                        int numero_telefono = sc.nextInt();
                        System.out.println("Escriba el tipo de cuenta que quiere agregar Prepago-Postpago");
                        String tipo_cuenta = sc.nextLine();
                        sc.nextLine();

                        sistema_comunicacion.crear_cuenta(numero_telefono, identificacion, tipo_cuenta);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese el ID de su cuenta asociada");
                        int id = sc.nextInt();
                        System.out.println("Ingrese el tipo de la llamada (INTERNACIONAL O NACIONAL).");
                        String tipo_llamada = sc.nextLine();
                        String pais = null;
                        if (tipo_llamada.equalsIgnoreCase("internacional")) {
                            System.out.println("Seleccione alguno de los paises disponibles: ");
                            for (Paises_disponibles paises : Paises_disponibles.values()) {
                                System.out.println(paises + ": ");
                            }
                            pais = sc.nextLine();
                        }
                        System.out.println("Indique la fecha de la llamada (yyyy-MM-dd)");
                        String fecha = sc.nextLine();
                        LocalDate fecha_real = IEmpresa.convertirTextoAFecha(fecha, "(yyyy-MM-dd)");
                        System.out.println("Indique el numero de telefono destinatario");
                        int telefono = sc.nextInt();
                        System.out.println("Indique la duracion de la llamada");
                        int duracion_llamada = sc.nextInt();

                        sistema_comunicacion.registrar_llamada(id, tipo_llamada, pais, fecha_real, telefono, duracion_llamada);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try{
                        System.out.println("Indique el ID de su cuenta");
                        long id = sc.nextLong();
                        System.out.println("Indique la fecha de la recarga");
                        String fecha_s = sc.nextLine();
                        LocalDate fecha = IEmpresa.convertirTextoAFecha(fecha_s,"(yyyy-MM-dd)");
                        System.out.println("Indique el valor de la recarga");
                        long valor = sc.nextLong();

                        sistema_comunicacion.agregar_recarga(id,fecha,valor);

                    }catch(Exception e){
                        System.out.println("Sucedio un error: " + e.getMessage());
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }while(opcion != 0);
    }
}
