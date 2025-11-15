
package main;

import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;
import service.VehiculoService;
import service.VehiculoServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class TestVehiculoService {

    public static void main(String[] args) {

        VehiculoService service = new VehiculoServiceImpl();

        // 1) Armo un seguro y un vehículo en memoria
        SeguroVehicular seguro = new SeguroVehicular(
                null,
                false,
                "Seguros Service",
                "POL-7777",
                Cobertura.TERCEROS,
                LocalDate.of(2031, 1, 1)
        );

        Vehiculo vehiculo = new Vehiculo(
                null,
                false,
                "AB777CD",
                "Fiat",
                "Pulse",
                2024,
                "CHASIS-777",
                seguro
        );

        // 2) Alta conjunta (transacción)
        Vehiculo creado = service.crearVehiculoConSeguro(vehiculo);
        System.out.println("Vehículo creado por Service: " + creado);

        // 3) Buscar por dominio
        Vehiculo buscado = service.obtenerPorDominio("AB777CD");
        System.out.println("Buscado por dominio: " + buscado);

        // 4) Listar todos
        List<Vehiculo> lista = service.listarTodos();
        System.out.println("Listado desde Service:");
        lista.forEach(System.out::println);

        // 5) Baja lógica de uno (vehículo + seguro)
        // service.bajaLogicaVehiculoYSeguro(creado.getId());
    }
}
