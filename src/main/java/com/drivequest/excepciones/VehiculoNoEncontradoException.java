/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.excepciones;

/**
 *
 * @author Andrea
 * Excepción personalizada para ser lanzada cuando se busca un vehículo
 * por su patente y no se encuentra en la flota.
 */
public class VehiculoNoEncontradoException extends Exception {

    public VehiculoNoEncontradoException(String message) {
        super(message);
    }
}
