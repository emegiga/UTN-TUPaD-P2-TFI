package dao;

import config.DatabaseConnection;
import entities.Cobertura;
import entities.SeguroVehicular;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeguroVehicularDaoImpl implements SeguroVehicularDao {

    private static final String INSERT_SQL = """
        INSERT INTO SeguroVehicular (eliminado, aseguradora, nroPoliza, cobertura, vencimiento)
        VALUES (?, ?, ?, ?, ?)
        """;

    private static final String SELECT_BY_ID_SQL = """
        SELECT id, eliminado, aseguradora, nroPoliza, cobertura, vencimiento
        FROM SeguroVehicular
        WHERE id = ?
        """;

    private static final String SELECT_ALL_SQL = """
        SELECT id, eliminado, aseguradora, nroPoliza, cobertura, vencimiento
        FROM SeguroVehicular
        """;

    private static final String UPDATE_SQL = """
        UPDATE SeguroVehicular
        SET eliminado = ?, aseguradora = ?, nroPoliza = ?, cobertura = ?, vencimiento = ?
        WHERE id = ?
        """;

    private static final String DELETE_LOGICO_SQL = """
        UPDATE SeguroVehicular
        SET eliminado = 1
        WHERE id = ?
        """;

    @Override
    public void crear(SeguroVehicular seguro, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, seguro.isEliminado());
            ps.setString(2, seguro.getAseguradora());
            ps.setString(3, seguro.getNroPoliza());
            ps.setString(4, seguro.getCobertura().name()); // enum a String
            ps.setDate(5, Date.valueOf(seguro.getVencimiento()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long idGenerado = rs.getLong(1);
                    seguro.setId(idGenerado);
                }
            }
        }
    }

    @Override
    public SeguroVehicular obtenerPorId(Long id, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSeguro(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<SeguroVehicular> obtenerTodos(Connection conn) throws SQLException {
        List<SeguroVehicular> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRowToSeguro(rs));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(SeguroVehicular seguro, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setBoolean(1, seguro.isEliminado());
            ps.setString(2, seguro.getAseguradora());
            ps.setString(3, seguro.getNroPoliza());
            ps.setString(4, seguro.getCobertura().name());
            ps.setDate(5, Date.valueOf(seguro.getVencimiento()));
            ps.setLong(6, seguro.getId());

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

    // Método helper para mapear una fila a objeto
    private SeguroVehicular mapRowToSeguro(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        boolean eliminado = rs.getBoolean("eliminado");
        String aseguradora = rs.getString("aseguradora");
        String nroPoliza = rs.getString("nroPoliza");
        String coberturaStr = rs.getString("cobertura");
        Cobertura cobertura = Cobertura.valueOf(coberturaStr);
        LocalDate vencimiento = rs.getDate("vencimiento").toLocalDate();

        return new SeguroVehicular(id, eliminado, aseguradora, nroPoliza, cobertura, vencimiento);
    }

    // Método de prueba "autónomo" (opcional) usando DatabaseConnection directamente
    public List<SeguroVehicular> obtenerTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return obtenerTodos(conn);
        }
    }
}

