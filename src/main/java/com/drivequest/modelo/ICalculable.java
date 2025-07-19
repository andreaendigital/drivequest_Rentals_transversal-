/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.drivequest.modelo;

/**
 * @author Andrea
 * 
 * Interfaz ICalculable
 * Define un contrato para todas las clases que necesiten realizar cálculos de arriendo.
 * Al usar una interfaz, promovemos el bajo acoplamiento, ya que cualquier clase,
 * incluso una que no sea un Vehiculo, podría implementar estas reglas de negocio.
 * También es el lugar ideal para definir constantes públicas.
 */
public interface ICalculable {
    // Constantes para los cálculos fiscales y de descuentos.
    // Son implícitamente public, static y final.
    double IVA = 0.19;
    double DESCUENTO_CARGA = 0.07;
    double DESCUENTO_PASAJEROS = 0.12;

    /**
     * Método que debe ser implementado por cualquier clase que firme este contrato.
     * Obliga a definir la lógica para calcular el total de un arriendo.
     * @param dias El número de días que se arrendará el vehículo.
     * @param precioBaseDia El precio base por día del vehículo.
     * @return El costo total del arriendo, incluyendo IVA y descuentos.
     */
    double calcularTotalArriendo(int dias, double precioBaseDia);
}
