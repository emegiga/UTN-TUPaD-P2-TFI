
package service;

import entities.Vehiculo;
import java.util.List;

public interface VehiculoService extends GenericService<Vehiculo> {

    // Alta conjunta de vehículo + seguro (transacción)
    Vehiculo crearVehiculoConSeguro(Vehiculo vehiculo);

    // ===== Métodos de lectura específicos =====

    // Lectura por dominio (campo clave de negocio)
    Vehiculo obtenerPorDominio(String dominio);

    // Lectura por id, con nombre más directo
    Vehiculo obtenerPorId(Long id);

    // Listado de todos, con nombre más directo
    List<Vehiculo> listarTodos();

    // ===== Operaciones de actualización/baja específicas =====

    Vehiculo actualizarVehiculoYSeguro(Vehiculo vehiculo);

    void bajaLogicaVehiculoYSeguro(Long vehiculoId);

    // Nota:
    // - insertar/actualizar/eliminar/getById/getAll vienen de GenericService<Vehiculo>
    // - obtenerPorId/listarTodos son alias más semánticos que reutilizamos en la implementación
}


