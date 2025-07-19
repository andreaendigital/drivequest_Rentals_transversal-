/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.excepciones;

/**
 *
 * @author Andrea
 * Excepción personalizada para ser lanzada cuando una patente no cumple
 * con el formato de validación requerido.
 * Hereda de Exception, lo que la convierte en una "checked exception".
 */
public class PatenteInvalidaException extends Exception {

    /**
     * Constructor que acepta un mensaje de error.
     * @param message El mensaje que describe la causa del error.
     */
    public PatenteInvalidaException(String message) {
        super(message);
    }
}
