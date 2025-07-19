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
 * 
 * Hilo para guardar el estado de la flota en el archivo en segundo plano.
 * Permite que el usuario siga interactuando con al aplicación mientras se guardan los datos
 */
public class GuardadorVehiculosThread extends Thread {
    private final GestionFlota gestionFlota;
    private final ManejadorArchivos manejadorArchivos;

    public GuardadorVehiculosThread(GestionFlota gestionFlota){
        this.gestionFlota = gestionFlota;
        this.manejadorArchivos = new ManejadorArchivos();
        //Asignar un nombre al hilo es una buena práctica para la depuración
        setName("GuardadorDeFlota");        
    }

    @Override
    public void run(){
        System.out.println("Iniciando guardado de datos en segundo plano... [ " + getName() + " ] ");
        //La operacion de I/O se ejcuta en este hilo, liberando al hilo principal.
        manejadorArchivos.guardarVehiculos(gestionFlota.getFlota());
        System.out.println("Guardado de Datos completado");
    }
    
}
