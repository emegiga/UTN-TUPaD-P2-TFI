
package dao;

import entities.SeguroVehicular;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SeguroVehicularDao {

    void crear(SeguroVehicular seguro, Connection conn) throws SQLException;

    SeguroVehicular obtenerPorId(Long id, Connection conn) throws SQLException;

    List<SeguroVehicular> obtenerTodos(Connection conn) throws SQLException;

    void actualizar(SeguroVehicular seguro, Connection conn) throws SQLException;

    void eliminarLogico(Long id, Connection conn) throws SQLException;
}

