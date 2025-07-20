/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.drivequest;

import com.drivequest.concurrencia.CargadorVehiculosThread;
import com.drivequest.negocio.GestionFlota;
import com.drivequest.ui.MenuConsola;

/**
 *
 * @author Andrea
 */
public class DriveQuest {

    public static void main(String[] args) {
        GestionFlota gestionFlota = new GestionFlota();
        
        // Carga previa de la flota desde el archivo CSV
        CargadorVehiculosThread cargador = new CargadorVehiculosThread(gestionFlota);
        cargador.start();
        try {
            cargador.join();  // Asegura que la carga esté terminada antes de mostrar el menú
        } catch (InterruptedException e) {
            System.err.println("Error al cargar la flota desde archivo.");
            Thread.currentThread().interrupt();
        }


        MenuConsola menu = new MenuConsola(gestionFlota);
        menu.iniciar();  

    }
}
