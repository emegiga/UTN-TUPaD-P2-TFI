/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Vehiculo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VehiculoDao {

    void crear(Vehiculo vehiculo, Connection conn) throws SQLException;

    Vehiculo obtenerPorId(Long id, Connection conn) throws SQLException;

    Vehiculo obtenerPorDominio(String dominio, Connection conn) throws SQLException;

    List<Vehiculo> obtenerTodos(Connection conn) throws SQLException;

    void actualizar(Vehiculo vehiculo, Connection conn) throws SQLException;

    void eliminarLogico(Long id, Connection conn) throws SQLException;
}
