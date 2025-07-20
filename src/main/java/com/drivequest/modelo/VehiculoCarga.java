/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequest.modelo;
import com.drivequest.excepciones.PatenteInvalidaException;

/**
 *
 * @author Andrea
 * 
 * Clase VehiculoCarga
 * Especialización de Vehiculo. Representa un vehículo destinado al transporte de carga.
 * Hereda los atributos y métodos de Vehiculo e implementa la interfaz ICalculable.
 * 
 */
public class VehiculoCarga extends Vehiculo implements ICalculable {
    
    private double capacidadCargaKg; // Atributo especifico para este tipo de vehiculo
    
     /**
    *Constructor que inicializa tanto los atributos propios como los de la clase padre, propaga PatenteInvalidaException,
    *ya que llama al constructor de la superclase que la lanza.
     * @param patente
     * @param marca
     * @param precioBaseDia
     * @param capacidadCargaKg
     * @throws com.drivequest.excepciones.PatenteInvalidaException
    */
    public VehiculoCarga(String patente, String marca, double precioBaseDia, double capacidadCargaKg) throws PatenteInvalidaException {
        //La primera linea DEBE ser la llamada al constructor de la clase padre (super).
        super(patente, marca, precioBaseDia);
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public double getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    public void setCapacidadCargaKg(double capacidadCargaKg) {
        this.capacidadCargaKg = capacidadCargaKg;
    }

    /**
     * Implementación específica del método abstracto de la clase padre.
     * Polimorfismo en acción: este método se comportará de forma diferente al de VehiculoPasajeros.
     * @return Una cadena con los datos completos del vehículo de carga.
     */
    
    
    @Override
    public String mostrarDatos() {
        return String.format("Tipo: Carga | Patente: %-8s | Marca: %-10s | Capacidad: %.1f Kg | Precio/Dia: $%.0f | Estado: %s",
                patente, marca, capacidadCargaKg, precioBaseDia, arrendado ? "Arrendado (" + diasArriendo + " dias)" : "Disponible");
    }

    /**
     * Implementación del método de la interfaz ICalculable.
     * Aplica la lógica de negocio específica para vehículos de carga.
     */
    @Override
    public double calcularTotalArriendo(int dias, double precioBaseDia) {
        double subtotal = dias * precioBaseDia;
        double descuento = subtotal * DESCUENTO_CARGA;
        double subtotalConDescuento = subtotal - descuento;
        double ivaCalculado = subtotalConDescuento * IVA;
        return subtotalConDescuento + ivaCalculado;
    }
    
}
