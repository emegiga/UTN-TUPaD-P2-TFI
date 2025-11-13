/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entities.Cobertura;
import entities.Vehiculo;
import java.time.LocalDate;

/**
 *
 * @author matut
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // objeto de prueba
        Vehiculo auto = new Vehiculo(
                1L, // id vehículo
                false, // eliminado
                "Toyota", // marca
                "Corolla", // modelo
                2020, // año
                "CHS123456XYZ", // número de chasis

                // Datos del seguro (composición)
                "La Caja", // aseguradora
                "POL-998877", // número de póliza
                Cobertura.TODO_RIESGO, // enum Cobertura
                LocalDate.of(2025, 5, 20) // vencimiento
        );
        System.out.println(auto.toString());
    }
}