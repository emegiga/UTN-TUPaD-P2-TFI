/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;


/**
 *
 * @author matut
 */
public class Vehiculo {
    private Long id;
    private boolean eliminado;
    private String marca;
    private String modelo;
    private int anio;
    private String nroChasis;
    private SeguroVehicular seguro;
    
    // constructor completo
    public Vehiculo(Long id, boolean eliminado, String marca, String modelo, int anio, String nroChasis, String aseguradora, String nroPoliza,
                    Cobertura cobertura, LocalDate vencimiento) {
        this.id = id;
        this.eliminado = eliminado;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.seguro = new SeguroVehicular(null, false, aseguradora, nroPoliza, cobertura, vencimiento);
    }
    
    // constructor vacio
    public Vehiculo() {
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", eliminado=" + eliminado + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", nroChasis=" + nroChasis + ", seguro=" + seguro + '}';
    }
    
}
