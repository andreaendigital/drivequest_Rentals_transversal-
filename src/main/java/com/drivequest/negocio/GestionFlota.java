/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.negocio;

import com.drivequest.excepciones.PatenteDuplicadaException;
import com.drivequest.excepciones.VehiculoYaArrendadoException;
import com.drivequest.excepciones.VehiculoNoEncontradoException;
import com.drivequest.modelo.Vehiculo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 *
 * @author Andrea
 */
public class GestionFlota {
    
    private Map<String, Vehiculo> flota;
    
    public GestionFlota() {
        this.flota = new ConcurrentHashMap<>();        
    }
    
    public Map<String, Vehiculo> getFlota() {
        return flota;
    }
    
  /**
   * Agrega un vehículo a la flota.No tiene return y lanza excepción si la patente ya existe.
   *
     * @param vehiculo 
     * @throws com.drivequest.excepciones.PatenteDuplicadaException 
   */
    
    public void agregarVehiculo(Vehiculo vehiculo) throws PatenteDuplicadaException {
        if(vehiculo == null || vehiculo.getPatente() == null ){
            //Se opta por no crear una excepción personalizada para este caso
            throw new IllegalArgumentException("El vehiculo o su patente no pueden ser nulos.") ;                   
        }
        
        //putIfAbsent es atómico. Si devuelve un valor no nulo, significa que la clave ya existía.
        if(flota.putIfAbsent(vehiculo.getPatente(), vehiculo) != null){
            throw new PatenteDuplicadaException("La patente ' " + vehiculo.getPatente() + " ' ya esta registrada en la flota.");            
        }
    }
    
    /**
     * Busca un vehículo por su patente.
     * Lanza una excepción si el vehículo no se encuentra
     * @param patente La patente a buscar
     * @return El objeto Vehiculo si se encuentra
     * @throws VehiculoNoEncontradoException si no existe ningún vehículo con esa patente
     */
    
    public Vehiculo buscarVehiculo(String patente) throws VehiculoNoEncontradoException{
        if(patente == null){
            throw new IllegalArgumentException("La patente de busqueda no puede ser nula.");
        }
        
        Vehiculo vehiculo = flota.get(patente.toUpperCase());
        if(vehiculo  == null){
            throw new VehiculoNoEncontradoException("No se encontro ningun vehiculo con la patente ' " + patente.toUpperCase() + " '. ");
        }
        return vehiculo;
    }
    
    /**
     * Método que encapsula toda la lógica de negocio para arrendar un vehículo.
     * @param patente La patente del vehículo a arrendar.
     * @param dias El número de días del arriendo
     * @throws com.drivequest.excepciones.VehiculoNoEncontradoException
     * @throw VehiculoNoEncontradoException si la patente no existe
     * @throw VehiculoYaArrendadoException si el vehiculo ya está arrendado
     */
    
    public void arrendarVehiculo(String patente, int dias) throws VehiculoNoEncontradoException, VehiculoYaArrendadoException {
        if (dias <= 0){
            //Se opta por no crear una excepción personalizada para este caso
            throw new IllegalArgumentException("El numero de dias de arriendo debe ser mayor a cero");
        }
        
        Vehiculo vehiculo = buscarVehiculo(patente); //Se reutiliza el método de búsqueda
        
        if (vehiculo.isArrendado()){
            throw new VehiculoYaArrendadoException("El vehiculo con patente ' " + patente + " ' ya se encuentra arrendado");
        }
        
        //Si todas las validaciones pasan, se actualiza el estado del vehiculo
        vehiculo.setArrendado(true);
        vehiculo.setDiasArriendo(dias);
        
    }
    
    public List<Vehiculo> listarTodosLosVehiculos(){
        return flota.values().stream().collect(Collectors.toList());
    }
    
    public List<Vehiculo> listarVehiculosArriendoLargo() {
        return flota.values().stream()
                .filter(v -> v.isArrendado() && v.getDiasArriendo() >= 7)
                .collect(Collectors.toList());
    }
    public void cargarFlota(Map<String, Vehiculo> nuevaFlota){
        if(nuevaFlota != null){
            this.flota.clear();
            this.flota.putAll(nuevaFlota);
        }
    }
    
    
    
}
