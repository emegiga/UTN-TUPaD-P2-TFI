package entities;

import java.time.LocalDate;

/* 
Entidad que representa una póliza de seguros (seguro vehicular).
Relación con Vehículo: Cada póliza corresponde a un vehículo específico. 
Tabla BD: SeguroVehicular
*/

public class SeguroVehicular extends BaseVehiculoSeguro{

    private String aseguradora;
    private String nroPoliza;
    private Cobertura cobertura;
    private LocalDate vencimiento;

    // constructor vacío
    public SeguroVehicular() {
        super();
    }

    // constructor completo
    public SeguroVehicular(Long id, boolean eliminado, String aseguradora,
            String nroPoliza, Cobertura cobertura, LocalDate vencimiento) {
        super(id, eliminado);
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
        this.cobertura = cobertura;
        this.vencimiento = vencimiento;
    }

    // Getters y setters
    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getNroPoliza() {
        return nroPoliza;
    }

    public void setNroPoliza(String nroPoliza) {
        this.nroPoliza = nroPoliza;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    @Override
    public String toString() {
        return ". [ID " + getId() + "] "
//                + ", eliminado=" + eliminado
                + "N° DE POLIZA: " + nroPoliza + "  FECHA DE VENCIMIENTO: " + vencimiento
                + "  ASEGURADORA: " + aseguradora + "  COBERTURA: " + cobertura;
    }
}
