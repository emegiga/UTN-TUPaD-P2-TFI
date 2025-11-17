package entities;

public abstract class BaseVehiculoSeguro {
    private Long id;
    private boolean eliminado;

    // constructores
    public BaseVehiculoSeguro(Long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public BaseVehiculoSeguro() {
        this.eliminado = false;
    }
    
    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

}
