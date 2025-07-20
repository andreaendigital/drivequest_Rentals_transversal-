/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.ui;

import com.drivequest.concurrencia.GeneradorVehiculosThread;
import com.drivequest.concurrencia.GuardadorVehiculosThread;
import com.drivequest.excepciones.PatenteDuplicadaException;
import com.drivequest.excepciones.PatenteInvalidaException;
import com.drivequest.excepciones.VehiculoNoEncontradoException;
import com.drivequest.excepciones.VehiculoYaArrendadoException;
import com.drivequest.modelo.ICalculable;
import com.drivequest.modelo.Vehiculo;
import com.drivequest.modelo.VehiculoCarga;
import com.drivequest.modelo.VehiculoPasajeros;
import com.drivequest.negocio.GestionFlota;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andrea
 * 
 * 
 */
public class MenuConsola {
    private final GestionFlota gestionFlota;
    private final Scanner scanner;
    
    public MenuConsola(GestionFlota gestionFlota){
        this.gestionFlota = gestionFlota;
        this.scanner = new Scanner (System.in);
    }
    
    public void iniciar(){
        int opcion = 0;
        while(opcion != 8){
            mostrarMenu();
            try{
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion){
                    case 1: agregarNuevoVehiculo(); break;
                    case 2: listarTodosLosVehiculos(); break;
                    case 3: gestionarArriendo(); break;
                    case 4: mostrarArriendosLargos(); break;
                    case 5: guardarDatosEnArchivo(); break;
                    case 6: generarVehiculosMasivamente(); break;
                    case 7: mostrarCantidadVehiculos(); break;
                    case 8:
                
                        System.out.println("Guardando cambios y saliendo del sistema... Por favor, espere.");
                        // Creamos una referencia al hilo para poder operar sobre él.
                        GuardadorVehiculosThread guardador = new GuardadorVehiculosThread(gestionFlota);
                        guardador.start(); // Lo iniciamos como antes.
                        try {
                            // NUEVO: El hilo principal se detiene aquí y espera hasta que el hilo 'guardador' termine su trabajo.
                            guardador.join();
                        } catch (InterruptedException e) {
                            System.err.println("El guardado fue interrumpido.");
                            // Es una buena práctica restaurar el estado de interrupción.
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Guardado completado. Saliendo.");
                        break;
                    default: 
                        System.out.println("Opcion no valida. Intente nuevamente.");
                }
                
            } catch (InputMismatchException e){
                System.err.println("Error: Debe ingresar un numero. Intente nuevamente");
                scanner.nextLine();
            }
            
            if(opcion !=8) presioneEnterParaContinuar();
        }
        scanner.close();
    }
    
    private void mostrarMenu(){
        System.out.println("");
         System.out.println("--- DRIVE QUEST RENTALS ---");
         System.out.println("");
         System.out.println("1. Agregar nuevo vehiculo");
         System.out.println("2. Listar todos los vehiculos");
         System.out.println("3. Gestionar Arriendo (Mostrar Boleta)");
         System.out.println("4. Mostrar vehiculos con arriendos largos (>= 7 dias)");
         System.out.println("5. Guardar datos de vehiculos en archivo");
         System.out.println("6. Generar vehiculos de prueba masivamente");
         System.out.println("7. Mostrar cantidad total de vehiculos");
         System.out.println("8. Salir del sistema");
         System.out.println("");
         System.out.println("Seleccione una opcion: ");
    }
    
    private void agregarNuevoVehiculo(){
        try{
            System.out.println("Ingrese el tipo de vehiculo (1: Carga, 2: Pasajeros):");
            int tipo = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Ingrese patente (formato: AAAA11): ");
            String patente = scanner.nextLine().toUpperCase();
            
            System.out.println("Ingrese marca:");
            String marca = scanner.nextLine();
            
            System.out.println("Ingrese precio base por dia: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            
            Vehiculo nuevoVehiculo;
            
            if(tipo == 1){
                
                System.out.println("Ingrese capacidad de carga (Kg): ");
                int capacidad = scanner.nextInt();
                scanner.nextLine();
                nuevoVehiculo = new VehiculoCarga(patente, marca, precio, capacidad);
                
            }else if (tipo == 2){
                
                System.out.println("Ingrese numero maximo de pasajeros: ");
                int pasajeros = scanner.nextInt();
                scanner.nextLine();
                nuevoVehiculo = new VehiculoPasajeros(patente, marca, precio, pasajeros);
                
            }else{
                System.out.println("Tipo de vehiculo no valido");
                return;
            }
            
            gestionFlota.agregarVehiculo(nuevoVehiculo);
            System.out.println("Vehiculo agregado con exito");           
            
            
        }catch (PatenteInvalidaException | PatenteDuplicadaException e) {
            System.err.println("Error al agregar vehiculo: " + e.getMessage());
        }catch (InputMismatchException e ) {
            System.err.println("Error: Entrada de datos invalida. Por favor, ingrese los datos en el formato correcto");
            scanner.nextLine();
        }
    }
    
    private void listarTodosLosVehiculos(){
        System.out.println("");
        System.out.println("-- LISTADO COMPLETO DE LA FLOTA --"); 
        
        List<Vehiculo> vehiculos = gestionFlota.listarTodosLosVehiculos();
        
        if(vehiculos.isEmpty()){
            System.out.println("La flota esta vacia");
        }else{
            vehiculos.forEach(v -> System.out.println(v.mostrarDatos()));
        }
    }
      
    private void gestionarArriendo(){
        try{
            System.out.println("Ingrese la patente del vehiculo a arrendar: ");
            String patente = scanner.nextLine().toUpperCase();
            
            //La busqeuda ya está implícita en la lógica de la boleta.
            
            Vehiculo vehiculo = gestionFlota.buscarVehiculo(patente);
            
            if(vehiculo.isArrendado()){
                System.out.println("Info: El vehiculo esta arrendado. No se puede generar una nueva Boleta ");
                return;
            }
            
            System.out.println("Ingrese cantidad de dias de arriendo: ");
            int dias = scanner.nextInt();
            scanner.nextLine();
            
            mostrarBoleta(vehiculo, dias);
            
            System.out.println("Confirma arriendo? (S/N): ");
            String confirma = scanner.nextLine();
            
            if(confirma.equalsIgnoreCase("S")){
                gestionFlota.arrendarVehiculo(patente, dias);
                System.out.println("Arriendo confirmado con exito");
                
            }else{
                System.out.println("Arriendo cancelado.");
            }
            
        }catch (VehiculoNoEncontradoException | VehiculoYaArrendadoException | IllegalArgumentException e) {
            System.err.println("Error al gestionar arriendo: " + e.getMessage());
        }catch (InputMismatchException e ) {
            System.err.println("Error: Debe ingresar un numero de dias valido");
            scanner.nextLine();
        }
    }
        
    private void mostrarBoleta(Vehiculo vehiculo, int dias){

        //Casting : vehiculo implementa interfaz ICalculable, lo que permite acceder a los metodos para calcular el total
        ICalculable calculador = (ICalculable) vehiculo; 
        double total = calculador.calcularTotalArriendo(dias, vehiculo.getPrecioBaseDia());
        double subtotal = dias * vehiculo.getPrecioBaseDia();
        double descuentoAplicado = 0;
        String tipoDescuento = "";

        if (vehiculo instanceof VehiculoCarga) {
            descuentoAplicado = subtotal * ICalculable.DESCUENTO_CARGA;
            tipoDescuento = "Carga (7%)";
        } else if (vehiculo instanceof VehiculoPasajeros) {
            descuentoAplicado = subtotal * ICalculable.DESCUENTO_PASAJEROS;
            tipoDescuento = "Pasajeros (12%)";
        }

        System.out.println("\n--- Previsualizacion de Boleta ---");
        System.out.println(vehiculo.mostrarDatos());
        System.out.println("---------------------------------");
        System.out.printf("Subtotal (%d dias a $%.0f): $%.2f\n", dias, vehiculo.getPrecioBaseDia(), subtotal);
        System.out.printf("Descuento (%s): -$%.2f\n", tipoDescuento, descuentoAplicado);
        System.out.printf("Neto: $%.2f\n", subtotal - descuentoAplicado);
        System.out.printf("IVA (19%%): $%.2f\n", (subtotal - descuentoAplicado) * ICalculable.IVA);
        System.out.printf("TOTAL A PAGAR: $%.2f\n", total);
        System.out.println("---------------------------------");
       
    }
    
    
    
    private void mostrarArriendosLargos(){
        System.out.println("\n--- Vehiculos con Arriendo Prolongado (>= 7 dias) ---");
        List<Vehiculo> vehiculos = gestionFlota.listarVehiculosArriendoLargo();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos con arriendos de 7 dias o mas.");
        } else {
            vehiculos.forEach(v -> System.out.println(v.mostrarDatos()));
        }
    }
    
    private void guardarDatosEnArchivo(){
        GuardadorVehiculosThread guardador = new GuardadorVehiculosThread(gestionFlota);
        guardador.start();
        System.out.println("Solicitud de guardado enviada. El proceso se completara en segundo plano.");
    
    }
     
    private void generarVehiculosMasivamente(){
        System.out.print("Cuantos vehiculos de prueba desea generar?: ");
        try {
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            if (cantidad > 0) {
                GeneradorVehiculosThread generador = new GeneradorVehiculosThread(gestionFlota, cantidad);
                generador.start();
                System.out.println("Solicitud de generacion masiva enviada. El proceso se completara en segundo plano.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Error: Debe ingresar un numero.");
            scanner.nextLine();
        }
    }
    private void mostrarCantidadVehiculos(){
         System.out.println("\nTotal de vehiculos en la flota: " + gestionFlota.getFlota().size());
    } 
    
    private void presioneEnterParaContinuar(){
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine(); 
    }
    
    
}
