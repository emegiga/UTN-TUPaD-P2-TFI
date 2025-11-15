
package service;

import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import dao.SeguroVehicularDaoImpl;
import dao.VehiculoDao;
import dao.VehiculoDaoImpl;
import entities.SeguroVehicular;
import entities.Vehiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoDao vehiculoDao;
    private final SeguroVehicularDao seguroDao;

    public VehiculoServiceImpl() {
        this.vehiculoDao = new VehiculoDaoImpl();
        this.seguroDao = new SeguroVehicularDaoImpl();
    }

    // ========= ALTA (Vehículo + Seguro) =========
    @Override
    public Vehiculo crearVehiculoConSeguro(Vehiculo vehiculo) {
        Connection conn = null;
        try {
            // 1) Conexión y comienzo de transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 2) Validaciones de negocio
            validarVehiculo(vehiculo);
            SeguroVehicular seguro = vehiculo.getSeguro();
            if (seguro == null) {
                throw new IllegalArgumentException("El vehículo debe tener un seguro asociado.");
            }
            validarSeguro(seguro);

            // 3) Persistir primero el seguro
            seguroDao.crear(seguro, conn);

            // 4) Persistir el vehículo usando el id del seguro recien generado
            vehiculo.setSeguro(seguro);
            vehiculoDao.crear(vehiculo, conn);

            // 5) Todo ok -> commit
            conn.commit();
            return vehiculo;

        } catch (Exception e) {
            // Algo falló -> rollback
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Error al crear vehículo y seguro: " + e.getMessage(), e);

        } finally {
            // Volver autoCommit a true y cerrar conexión
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ========= CONSULTAS SIN TRANSACCIÓN COMPLEJA =========

    @Override
    public Vehiculo obtenerPorId(Long id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return vehiculoDao.obtenerPorId(id, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener vehículo por id: " + e.getMessage(), e);
        }
    }

    @Override
    public Vehiculo obtenerPorDominio(String dominio) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return vehiculoDao.obtenerPorDominio(dominio, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener vehículo por dominio: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Vehiculo> listarTodos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return vehiculoDao.obtenerTodos(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar vehículos: " + e.getMessage(), e);
        }
    }

    // ========= ACTUALIZACIÓN CONJUNTA =========

    @Override
    public Vehiculo actualizarVehiculoYSeguro(Vehiculo vehiculo) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            validarVehiculo(vehiculo);
            SeguroVehicular seguro = vehiculo.getSeguro();
            if (seguro == null || seguro.getId() == null) {
                throw new IllegalArgumentException("El vehículo debe tener un seguro válido para actualizar.");
            }
            validarSeguro(seguro);

            // Actualizo primero el seguro
            seguroDao.actualizar(seguro, conn);
            // Luego el vehículo
            vehiculoDao.actualizar(vehiculo, conn);

            conn.commit();
            return vehiculo;

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Error al actualizar vehículo y seguro: " + e.getMessage(), e);

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ========= BAJA LÓGICA CONJUNTA =========

    @Override
    public void bajaLogicaVehiculoYSeguro(Long vehiculoId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 1) Traigo el vehículo (con su seguro)
            Vehiculo vehiculo = vehiculoDao.obtenerPorId(vehiculoId, conn);
            if (vehiculo == null) {
                throw new IllegalArgumentException("No existe vehículo con id " + vehiculoId);
            }

            Long seguroId = vehiculo.getSeguro().getId();

            // 2) Baja lógica de seguro y vehículo
            seguroDao.eliminarLogico(seguroId, conn);
            vehiculoDao.eliminarLogico(vehiculoId, conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Error al realizar baja lógica: " + e.getMessage(), e);

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ========= VALIDACIONES DE NEGOCIO =========

    private void validarVehiculo(Vehiculo v) {
        if (v.getDominio() == null || v.getDominio().isBlank()) {
            throw new IllegalArgumentException("El dominio no puede estar vacío.");
        }
        if (v.getDominio().length() > 10) {
            throw new IllegalArgumentException("El dominio supera los 10 caracteres.");
        }
        if (v.getMarca() == null || v.getMarca().isBlank()) {
            throw new IllegalArgumentException("La marca es obligatoria.");
        }
        if (v.getModelo() == null || v.getModelo().isBlank()) {
            throw new IllegalArgumentException("El modelo es obligatorio.");
        }
        // Año simple: no futuro ridículo
        int anioActual = LocalDate.now().getYear();
        if (v.getAnio() < 1900 || v.getAnio() > anioActual + 1) {
            throw new IllegalArgumentException("El año del vehículo es inválido.");
        }
        // nroChasis puede ser null, pero si viene, validar longitud
        if (v.getNroChasis() != null && v.getNroChasis().length() > 50) {
            throw new IllegalArgumentException("El número de chasis supera los 50 caracteres.");
        }
    }

    private void validarSeguro(SeguroVehicular s) {
        if (s.getAseguradora() == null || s.getAseguradora().isBlank()) {
            throw new IllegalArgumentException("La aseguradora es obligatoria.");
        }
        if (s.getAseguradora().length() > 80) {
            throw new IllegalArgumentException("La aseguradora supera los 80 caracteres.");
        }
        if (s.getNroPoliza() == null || s.getNroPoliza().isBlank()) {
            throw new IllegalArgumentException("El número de póliza es obligatorio.");
        }
        if (s.getNroPoliza().length() > 50) {
            throw new IllegalArgumentException("El número de póliza supera los 50 caracteres.");
        }
        if (s.getCobertura() == null) {
            throw new IllegalArgumentException("La cobertura es obligatoria.");
        }
        if (s.getVencimiento() == null) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria.");
        }
        if (!s.getVencimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La póliza debe tener vencimiento futuro.");
        }
    }
    
        // ====== Implementación de GenericService<Vehiculo> ======

    @Override
    public Vehiculo insertar(Vehiculo vehiculo) {
        // Reutilizamos la lógica de alta conjunta
        return crearVehiculoConSeguro(vehiculo);
    }

    @Override
    public Vehiculo actualizar(Vehiculo vehiculo) {
        // Reutilizamos la lógica de actualización conjunta
        return actualizarVehiculoYSeguro(vehiculo);
    }

    @Override
    public void eliminar(Long id) {
        // Reutilizamos la baja lógica conjunta
        bajaLogicaVehiculoYSeguro(id);
    }

    @Override
    public Vehiculo getById(Long id) {
        return obtenerPorId(id);
    }

    @Override
    public List<Vehiculo> getAll() {
        return listarTodos();
    }

}
