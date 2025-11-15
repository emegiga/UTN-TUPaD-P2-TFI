package main;

import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import dao.SeguroVehicularDaoImpl;
import entities.Cobertura;
import entities.SeguroVehicular;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TestSeguroDao {

    public static void main(String[] args) {
        SeguroVehicularDao dao = new SeguroVehicularDaoImpl();

        try (Connection conn = DatabaseConnection.getConnection()) {

            // 1) Crear uno nuevo
            SeguroVehicular nuevo = new SeguroVehicular(
                    null,
                    false,
                    "Prueba Seguros SA",
                    "POL-9999",
                    Cobertura.RC,
                    LocalDate.of(2030, 1, 1)
            );

            dao.crear(nuevo, conn);
            System.out.println("Creado seguro con id: " + nuevo.getId());

            // 2) Obtener por id
            SeguroVehicular obtenido = dao.obtenerPorId(nuevo.getId(), conn);
            System.out.println("Obtenido: " + obtenido);

            // 3) Listar todos
            List<SeguroVehicular> lista = dao.obtenerTodos(conn);
            System.out.println("Listado de seguros:");
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
