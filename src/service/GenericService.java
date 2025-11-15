
package service;

import java.util.List;

public interface GenericService<T> {

    T insertar(T entidad);

    T actualizar(T entidad);

    void eliminar(Long id);

    T getById(Long id);

    List<T> getAll();
}
