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
public class SeguroVehicular {
    private Long id;
    private boolean eliminado;
    private String aseguradora;
    private String nroPoliza;
    private Cobertura cobertura;
    private LocalDate vencimiento;    

    // constructor completo
    public SeguroVehicular(Long id, boolean eliminado, String aseguradora, String nroPoliza, Cobertura cobertura, LocalDate vencimiento) {
        this.id = id;
        this.eliminado = eliminado;
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
        this.cobertura = cobertura;
        this.vencimiento = vencimiento;
    }
    
    // constructor vacio
    public SeguroVehicular() {
    }

    // getter cobertura
    public Cobertura getCobertura() {
        return cobertura;
    }

    // tostring
    @Override
    public String toString() {
        return "SeguroVehicular{" + "id=" + id + ", eliminado=" + eliminado + ", aseguradora=" + aseguradora + ", nroPoliza=" + nroPoliza + ", cobertura=" + cobertura + ", vencimiento=" + vencimiento + '}';
    }
}
