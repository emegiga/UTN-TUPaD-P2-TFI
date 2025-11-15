
package service;

import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import dao.SeguroVehicularDaoImpl;
import entities.SeguroVehicular;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SeguroVehicularServiceImpl implements SeguroVehicularService {

    private final SeguroVehicularDao seguroDao;

    public SeguroVehicularServiceImpl() {
        this.seguroDao = new SeguroVehicularDaoImpl();
    }

    @Override
    public SeguroVehicular insertar(SeguroVehicular seguro) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            seguroDao.crear(seguro, conn);
            return seguro;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar seguro: " + e.getMessage(), e);
        }
    }

    @Override
    public SeguroVehicular actualizar(SeguroVehicular seguro) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            seguroDao.actualizar(seguro, conn);
            return seguro;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar seguro: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            seguroDao.eliminarLogico(id, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar (lógico) seguro: " + e.getMessage(), e);
        }
    }

    @Override
    public SeguroVehicular getById(Long id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return seguroDao.obtenerPorId(id, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener seguro por id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<SeguroVehicular> getAll() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return seguroDao.obtenerTodos(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar seguros: " + e.getMessage(), e);
        }
    }
}

