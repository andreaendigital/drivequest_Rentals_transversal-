/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.modelo;
import com.drivequest.excepciones.PatenteInvalidaException;
import java.util.regex.Pattern;
/**
 * @author Andrea
 * 
 * Clase Abstracta Vehiculo
 * Representa la base para cualquier tipo de vehículo en la flota.
 * Es 'abstract' porque no queremos que se puedan crear instancias de "Vehiculo" genéricos.
 * Un vehículo debe ser de un tipo específico (Carga, Pasajeros, etc.).
 * Contiene todos los atributos y métodos comunes a todos los vehículos.
 */
public abstract class Vehiculo {

    // Atributos comunes a toda la flota. Protegidos para que las clases hijas puedan acceder.
    protected String patente;
    protected String marca;
    protected double precioBaseDia;
    protected boolean arrendado;
    protected int diasArriendo;

    // Se define un patrón de validación para las patentes usando una expresión regular.
    // Patrón: 4 letras seguidas de 2 números (ej: ABCD12).
    private static final Pattern PATENTE_PATTERN = Pattern.compile("^[A-Z]{4}\\d{2}$");
    
     /**
     * Constructor sobrecargado.
     * Inicializa los datos comunes y puede lanzar PatenteInvalidaException.
     * Esto obliga a que cualquier código que cree un Vehiculo maneje la posibilidad de una patente inválida.
     */
    
    public Vehiculo(String patente, String marca, double precioBaseDia) throws PatenteInvalidaException {
        // Se encapsula la validación en un método para reutilizarla en el setter.
        validarYAsignarPatente(patente);
        this.marca = marca;
        this.precioBaseDia = precioBaseDia;
        this.arrendado = false; // Por defecto, un vehículo nuevo no está arrendado.
        this.diasArriendo = 0;
    }
    
    //Método privado para centralizar la lógica de validación de la patente.
    private void validarYAsignarPatente(String patente) throws PatenteInvalidaException {
        if (patente == null || !PATENTE_PATTERN.matcher(patente.toUpperCase()).matches()) {
            throw new PatenteInvalidaException("La patente '" + patente + "' es inválida. Debe tener el formato AAAA11.");
        }
        this.patente = patente.toUpperCase();
    }
    
    
    
    // Getters y Setters para un correcto encapsulamiento de los datos.
    public String getPatente() {
        return patente;
    }

    //El setter también valida y puede lanzar la excepción.
    public void setPatente(String patente) throws PatenteInvalidaException {
        validarYAsignarPatente(patente);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecioBaseDia() {
        return precioBaseDia;
    }

    public void setPrecioBaseDia(double precioBaseDia) {
        this.precioBaseDia = precioBaseDia;
    }

    public boolean isArrendado() {
        return arrendado;
    }

    public void setArrendado(boolean arrendado) {
        this.arrendado = arrendado;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    /**
     * Método abstracto para mostrar los datos.
     * Al ser abstracto, FORZAMOS a que cada clase hija (concreta) deba proporcionar
     * su propia implementación. Esto es un pilar del polimorfismo.
     * @return Una cadena de texto con la información formateada del vehículo.
     */
    public abstract String mostrarDatos();
}
