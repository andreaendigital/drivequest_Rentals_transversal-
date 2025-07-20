/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.concurrencia;

import com.drivequest.negocio.GestionFlota;
import com.drivequest.persistencia.ManejadorArchivos;

/**
 *
 * @author Andrea
 * Hilo para cargar los vehiculos desde el archivo en segundo plano.
 * Esto evita que la interfaz de usuario se congele al inciar la aplicación si el archivo es grande.
 * 
 */
public class CargadorVehiculosThread extends Thread {
    private final GestionFlota gestionFlota;
    private final ManejadorArchivos manejadorArchivos;
    
    public CargadorVehiculosThread(GestionFlota gestionFlota){
        this.gestionFlota = gestionFlota;
        this.manejadorArchivos = new ManejadorArchivos();
        //Asignar un nombre al hilo es una buena práctica para la depuración
        setName("CargadorDeFlota");        
    }

    @Override
    public void run(){
        System.out.println("Iniciando carga de datos en segundo plano... [ " + getName() + " ] ");
        //La operacion de I/O se ejcuta en este hilo, no en el principal.
        gestionFlota.cargarFlota(manejadorArchivos.cargarVehiculos());
        System.out.println("Carga de Datos completada " + gestionFlota.getFlota().size() + " Vehiculos Cargados");
    }
    
}
