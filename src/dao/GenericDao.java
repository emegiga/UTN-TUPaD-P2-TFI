package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    void crear(T entidad, Connection conn) throws SQLException;

    T obtenerPorId(Long id, Connection conn) throws SQLException;

    List<T> obtenerTodos(Connection conn) throws SQLException;

    void actualizar(T entidad, Connection conn) throws SQLException;

    void eliminarLogico(Long id, Connection conn) throws SQLException;
}

