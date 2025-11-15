/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;



public class Vehiculo {
    private Long id;
    private boolean eliminado;
    private String dominio;
    private String marca;
    private String modelo;
    private int anio;
    private String nroChasis;
    private SeguroVehicular seguro;

    public Vehiculo() {
    }

    public Vehiculo(Long id, boolean eliminado, String dominio, String marca,
                    String modelo, int anio, String nroChasis, SeguroVehicular seguro) {
        this.id = id;
        this.eliminado = eliminado;
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.seguro = seguro;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public String getDominio() { return dominio; }
    public void setDominio(String dominio) { this.dominio = dominio; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getNroChasis() { return nroChasis; }
    public void setNroChasis(String nroChasis) { this.nroChasis = nroChasis; }

    public SeguroVehicular getSeguro() { return seguro; }
    public void setSeguro(SeguroVehicular seguro) { this.seguro = seguro; }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", eliminado=" + eliminado +
                ", dominio='" + dominio + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", nroChasis='" + nroChasis + '\'' +
                ", seguro=" + seguro +
                '}';
    }
}
