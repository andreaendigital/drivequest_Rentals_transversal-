/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.modelo;
import com.drivequest.excepciones.PatenteInvalidaException;

/**
 *
 * @author Andrea
 */
public class VehiculoPasajeros extends Vehiculo implements ICalculable {

    private int maxPasajeros;

    /**
     *Constructor que inicializa tanto los atributos propios como los de la clase padre, propaga PatenteInvalidaException,
     *ya que llama al constructor de la superclase que la lanza.
     */
    public VehiculoPasajeros(String patente, String marca, double precioBaseDia, int maxPasajeros) throws PatenteInvalidaException {
        super(patente, marca, precioBaseDia);
        this.maxPasajeros = maxPasajeros;
    }
    
    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public String mostrarDatos() {
        return String.format("Tipo: Pasajero| Patente: %-8s | Marca: %-10s | Pasajeros: %-2d | Precio/Dia: $%.0f | Estado: %s",
                patente, marca, maxPasajeros, precioBaseDia, arrendado ? "Arrendado (" + diasArriendo + " dias)" : "Disponible");
    }

    @Override
    public double calcularTotalArriendo(int dias, double precioBaseDia) {
        double subtotal = dias * precioBaseDia;
        double descuento = subtotal * DESCUENTO_PASAJEROS;
        double subtotalConDescuento = subtotal - descuento;
        double ivaCalculado = subtotalConDescuento * IVA;
        return subtotalConDescuento + ivaCalculado;
    }
}

