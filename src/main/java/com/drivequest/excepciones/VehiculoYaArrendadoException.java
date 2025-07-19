/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.excepciones;

/**
 *
 * @author Andrea
 * Excepción personalizada para ser lanzada cuando se intenta realizar uan operación
 * de arriendo sobre un vehículo que ya se encuentra arrendado.
 */
public class VehiculoYaArrendadoException extends Exception {
    public VehiculoYaArrendadoException(String message){
        super(message);
    }
}
