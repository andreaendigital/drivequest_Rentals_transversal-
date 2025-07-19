/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.persistencia;

import com.drivequest.excepciones.PatenteInvalidaException;
import com.drivequest.modelo.Vehiculo;
import com.drivequest.modelo.VehiculoCarga;
import com.drivequest.modelo.VehiculoPasajeros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Andrea
 * 
 * Clase ManejadorArchivos
 * Se encarga de la persistencia de los datos de la flota en archivos de texto CSV
 */
public class ManejadorArchivos {
    private static final String NOMBRE_ARCHIVO = "flota_drivequest.csv";
    
    /**
     * Guarda el estado actual de la flota en un archivo CSV
     * Utiliza syncrhonized para prevenir condiciones de carrera
     * Esto asegura que solo un hilo pueda ejecutar este método a la vez,
     * evitando qeu el archivo se corrompa por escrituras simulutáneas.
     * @param flota El mapa de vehículos a persistir
     */
    
    public synchronized void guardarVehiculos (Map<String, Vehiculo> flota){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))){
            for (Vehiculo v : flota.values()){
                String linea;
                if(v instanceof VehiculoCarga){
                    VehiculoCarga vc = (VehiculoCarga) v;
                    linea = String.format("CARGA, %s, %s, %.0f, %.1f, %b, %d",
                            vc.getPatente(), vc.getMarca(), vc.getPrecioBaseDia(),
                            vc.getCapacidadCargaKg(), vc.isArrendado(), vc.getDiasArriendo() );
                    
                } else{
                    VehiculoPasajeros vp = (VehiculoPasajeros) v;
                    linea = String.format("PASAJERO, %s, %s, %.0f, %d, %b, %d",
                            vp.getPatente(), vp.getMarca(), vp.getPrecioBaseDia(),
                            vp.getMaxPasajeros(), vp.isArrendado(), vp.getDiasArriendo() );
                }
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los datos de la flota: " + e.getMessage() );
        }
    }
    
    /**
     * Carga los datos de los vehículos desde el archivo CSV
     * @return Un ConcurrrentHashMap con la flota cargada desde el archivo.
     */
    
    public Map<String, Vehiculo> cargarVehiculos(){
        Map<String, Vehiculo> flotaCargada = new ConcurrentHashMap<>();
        File archivo = new File(NOMBRE_ARCHIVO);
        
        if(!archivo.exists()){
            System.out.println("Archivo de datos no encontrado. Se iniciara con una flota vacia");
            return flotaCargada;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=reader.readLine()) != null){
                try{
                    String[] partes = linea.split(",");
                    if(partes.length < 7) continue;
                    
                    String tipo = partes[0];
                    String patente = partes[1];
                    String marca = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    double atributoEspecifico = Double.parseDouble(partes[4]);
                    boolean arrendado = Boolean.parseBoolean(partes[5]);
                    int dias = Integer.parseInt(partes[6]);
                    
                    Vehiculo vehiculo;
                    if("CARGA".equals(tipo)){
                        vehiculo = new VehiculoCarga(patente, marca, precio, atributoEspecifico);
                    }else{
                        vehiculo = new VehiculoPasajeros(patente, marca, precio, (int) atributoEspecifico);
                    }
                    vehiculo.setArrendado(arrendado);
                    vehiculo.setDiasArriendo(dias);
                    
                    flotaCargada.put(patente.toUpperCase(), vehiculo);
                    
                    //Capturamos la excpeción de patente invalida durante la carga
                    
                } catch (PatenteInvalidaException e) {
                    System.err.println("Error al cargar vehiculo. " + e.getMessage() + " . Se omitira la linea: ' " + linea + " ' ");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error al procesar una linea del archivo: ' " + linea + " ' . Se omitira. Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar datos de la flota: " + e.getMessage());
        }
        return flotaCargada;
        
    }
    
    
    
    
}
