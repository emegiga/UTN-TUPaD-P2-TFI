/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entities.Cobertura;
import entities.Vehiculo;
import entities.SeguroVehicular;
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
        
        SeguroVehicular segundaSeguros = new SeguroVehicular(
        
        );
        
    
        
        // objeto de prueba
        Vehiculo auto = new Vehiculo(
                1L, // id vehículo
                false, // eliminado
                "Dominio", //dominio
                "Toyota", // marca
                "Corolla", // modelo
                2020, // año
                "CHS123456XYZ", // número de chasis
                segundaSeguros
      );
        System.out.println(auto.toString());
    }
}