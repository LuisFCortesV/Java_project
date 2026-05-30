package com.puj.proyecto.Persistencia;

import com.puj.proyecto.Excepciones.numero_identificacion_ya_existente;
import com.puj.proyecto.Model.Cliente;
import com.puj.proyecto.Model.Empresa;
import com.puj.proyecto.Model.IEmpresa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manejo_archivos {
    public static ArrayList<Cliente> cargar_clientes(IEmpresa empresa,String localizacion_archivo){
            ArrayList<Cliente> clientes_actuales = empresa.getClientes();
                try (BufferedReader reader = new BufferedReader(new FileReader(localizacion_archivo))){
                    String linea;
                    while((linea = reader.readLine()) != null && !linea.equals("#FIN")){
                        if(linea.startsWith("#")){
                            continue;
                        }
                        String[] partes = linea.split("\\*");
                        String nombre = partes[0];
                        String n_identificacion = partes[1];
                        String direccion = partes[2];
                        for(Cliente c : clientes_actuales){
                            if(c.getIdentificacion().equals(n_identificacion)){
                                throw new numero_identificacion_ya_existente(   );
                            }
                        }
                        Cliente cliente_new = new Cliente(direccion,n_identificacion,nombre,"CC");
                        empresa.getClientes().add(cliente_new);
                    }
                }catch(IOException e){
                    System.out.println("Error al leer lista desde texto: " + e.getMessage());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                return empresa.getClientes();
            }
        }
