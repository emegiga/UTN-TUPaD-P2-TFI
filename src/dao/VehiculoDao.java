package dao;

import entities.Vehiculo;
import java.sql.Connection;
import java.sql.SQLException;

public interface VehiculoDao extends GenericDao<Vehiculo> {

    // Método específico extra de Vehículo
    Vehiculo obtenerPorDominio(String dominio, Connection conn) throws SQLException;
}

