/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.concurrencia;

import com.drivequest.excepciones.PatenteDuplicadaException;
import com.drivequest.excepciones.PatenteInvalidaException;
import com.drivequest.modelo.VehiculoCarga;
import com.drivequest.modelo.VehiculoPasajeros;
import com.drivequest.negocio.GestionFlota;
import java.util.Random;

/**
 *
 * @author Andrea
 * 
 * Hilo para generar uan gran cantidad de vehiculos de forma automática
 */
public class GeneradorVehiculosThread extends Thread {
    private final GestionFlota gestionFlota;
    private final int cantidadAGenerar;
    private final Random random = new Random();
    
    public GeneradorVehiculosThread(GestionFlota gestionFlota, int cantidadAGenerar ){
        this.gestionFlota = gestionFlota;
        this.cantidadAGenerar = cantidadAGenerar;
        setName("Generador Masivo de Vehiculos");
    }
    
    @Override
    public void run(){
        System.out.println("Iniciando generacion masiva de " + cantidadAGenerar + " vehiculos...");
        System.out.println("Ejecutandose [ " + getName() + " ] ");
        
        int generadosExitosamente = 0 ;
        for(int i=0; i<cantidadAGenerar; i++){
            //Genera una patente aleatoria con formato valido AAAA11
            String patente = String.format("%c%c%c%c%02d",
                    (char)('A' + random.nextInt(26)), (char)('A' + random.nextInt(26)),
                    (char)('A' + random.nextInt(26)), (char)('A' + random.nextInt(26)),
                    random.nextInt(100));
                    
            //el metodo run de un hilo no puede lanzar excpeciones "checked"
            //por lo tanto, las manejamos internamente en un try-catch
            
            try{
                if(random.nextBoolean()){
                    VehiculoCarga vc = new VehiculoCarga(patente, "Ford", 60000, 1000 + random.nextInt(2000));
                    gestionFlota.agregarVehiculo(vc);
                }else{
                    VehiculoPasajeros vp = new VehiculoPasajeros(patente, "Toyota", 50000, 4 + random.nextInt(4) );
                    gestionFlota.agregarVehiculo(vp);        
                }
                generadosExitosamente++;
            } catch (PatenteInvalidaException |  PatenteDuplicadaException e){
                //En una generación masiva, es normal tener colisiones de patentes
                //Se decide no reportarlas como error grave, simplemente las ignoramos y continuamos.
            }
                    
        }
        System.out.println("Generacion Masiva completada. Se agregaron " + generadosExitosamente + " vehiculos nuevos");
        System.out.println("[ " + getName() + " ]  Finalizo ejecucion.");
    }
    
    
    
}
