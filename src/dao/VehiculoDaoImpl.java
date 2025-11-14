/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDaoImpl implements VehiculoDao {

    private static final String INSERT_SQL = """
        INSERT INTO Vehiculo (eliminado, dominio, marca, modelo, anio, nroChasis, seguro_id)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

    private static final String SELECT_BASE = """
        SELECT 
            v.id AS v_id,
            v.eliminado AS v_eliminado,
            v.dominio,
            v.marca,
            v.modelo,
            v.anio,
            v.nroChasis,
            
            s.id AS s_id,
            s.eliminado AS s_eliminado,
            s.aseguradora,
            s.nroPoliza,
            s.cobertura,
            s.vencimiento
        FROM Vehiculo v
        JOIN SeguroVehicular s ON v.seguro_id = s.id
        """;

    private static final String SELECT_BY_ID_SQL = SELECT_BASE + " WHERE v.id = ?";

    private static final String SELECT_BY_DOMINIO_SQL = SELECT_BASE + " WHERE v.dominio = ?";

    private static final String SELECT_ALL_SQL = SELECT_BASE;

    private static final String UPDATE_SQL = """
        UPDATE Vehiculo
        SET eliminado = ?, dominio = ?, marca = ?, modelo = ?, anio = ?, nroChasis = ?, seguro_id = ?
        WHERE id = ?
        """;

    private static final String DELETE_LOGICO_SQL = """
        UPDATE Vehiculo
        SET eliminado = 1
        WHERE id = ?
        """;

    @Override
    public void crear(Vehiculo vehiculo, Connection conn) throws SQLException {
        if (vehiculo.getSeguro() == null || vehiculo.getSeguro().getId() == null) {
            throw new IllegalArgumentException("El vehículo debe tener un seguro con id asignado antes de crearlo");
        }

        try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, vehiculo.isEliminado());
            ps.setString(2, vehiculo.getDominio());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAnio());
            ps.setString(6, vehiculo.getNroChasis());
            ps.setLong(7, vehiculo.getSeguro().getId());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    vehiculo.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Vehiculo obtenerPorId(Long id, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToVehiculo(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Vehiculo obtenerPorDominio(String dominio, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_BY_DOMINIO_SQL)) {
            ps.setString(1, dominio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToVehiculo(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Vehiculo> obtenerTodos(Connection conn) throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRowToVehiculo(rs));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Vehiculo vehiculo, Connection conn) throws SQLException {
        if (vehiculo.getSeguro() == null || vehiculo.getSeguro().getId() == null) {
            throw new IllegalArgumentException("El vehículo debe tener un seguro con id asignado para actualizar");
        }

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setBoolean(1, vehiculo.isEliminado());
            ps.setString(2, vehiculo.getDominio());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAnio());
            ps.setString(6, vehiculo.getNroChasis());
            ps.setLong(7, vehiculo.getSeguro().getId());
            ps.setLong(8, vehiculo.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarLogico(Long id, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(DELETE_LOGICO_SQL)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private Vehiculo mapRowToVehiculo(ResultSet rs) throws SQLException {
        // Datos del vehículo (prefijo v_)
        Long vId = rs.getLong("v_id");
        boolean vEliminado = rs.getBoolean("v_eliminado");
        String dominio = rs.getString("dominio");
        String marca = rs.getString("marca");
        String modelo = rs.getString("modelo");
        int anio = rs.getInt("anio");
        String nroChasis = rs.getString("nroChasis");

        // Datos del seguro (prefijo s_)
        Long sId = rs.getLong("s_id");
        boolean sEliminado = rs.getBoolean("s_eliminado");
        String aseguradora = rs.getString("aseguradora");
        String nroPoliza = rs.getString("nroPoliza");
        String coberturaStr = rs.getString("cobertura");
        Cobertura cobertura = Cobertura.valueOf(coberturaStr);
        LocalDate vencimiento = rs.getDate("vencimiento").toLocalDate();

        SeguroVehicular seguro = new SeguroVehicular(
                sId,
                sEliminado,
                aseguradora,
                nroPoliza,
                cobertura,
                vencimiento
        );

        return new Vehiculo(
                vId,
                vEliminado,
                dominio,
                marca,
                modelo,
                anio,
                nroChasis,
                seguro
        );
    }
}

