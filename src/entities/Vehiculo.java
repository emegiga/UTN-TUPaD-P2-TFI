package entities;

/* 
Entidad que representa un vehículo.
Relación con SeguroVehicular: Cada vehículo tiene una única póliza de seguro asociada.
Tabla BD: Vehiculo
*/

public class Vehiculo extends BaseVehiculoSeguro{

    private String dominio;
    private String marca;
    private String modelo;
    private int anio;
    private String nroChasis;
    private SeguroVehicular seguro;

    // constructor vacío
    public Vehiculo() {
        super();
    }

    // constructor completo
    public Vehiculo(Long id, boolean eliminado, String dominio, String marca,
            String modelo, int anio, String nroChasis, SeguroVehicular seguro) {
        super(id, eliminado);
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.seguro = seguro;
    }

    // Getters y setters
    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public SeguroVehicular getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroVehicular seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return ". [ID " + getId() + "] "
//                + ", eliminado=" + eliminado
                + "N° DE POLIZA: " + seguro.getNroPoliza()
                + "  MARCA: " + marca + "  MODELO: " + modelo + "  N° CHASIS: " + nroChasis
                + "  DOMINIO: " + dominio + "  AÑO: " + anio;
    }
}
