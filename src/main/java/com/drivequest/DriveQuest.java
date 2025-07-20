/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.drivequest;

import com.drivequest.negocio.GestionFlota;
import com.drivequest.ui.MenuConsola;

/**
 *
 * @author Andrea
 */
public class DriveQuest {

    public static void main(String[] args) {
        GestionFlota gestionFlota = new GestionFlota();
        MenuConsola menu = new MenuConsola(gestionFlota);
        menu.iniciar();  

    }
}
