/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import config.DatabaseConnection;
import dao.VehiculoDao;
import dao.VehiculoDaoImpl;
import dao.SeguroVehicularDao;
import dao.SeguroVehicularDaoImpl;
import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TestVehiculoDao {

    public static void main(String[] args) {
        VehiculoDao vehiculoDao = new VehiculoDaoImpl();
        SeguroVehicularDao seguroDao = new SeguroVehicularDaoImpl();

        try (Connection conn = DatabaseConnection.getConnection()) {

            // 1) Crear un seguro nuevo para el vehículo
            SeguroVehicular seg = new SeguroVehicular(
                    null,
                    false,
                    "Seguros UTN",
                    "POL-2025",
                    Cobertura.TODO_RIESGO,
                    LocalDate.of(2030, 12, 31)
            );
            seguroDao.crear(seg, conn);
            System.out.println("Seguro creado con id: " + seg.getId());

            // 2) Crear el vehículo asociado a ese seguro
            Vehiculo nuevo = new Vehiculo(
                    null,
                    false,
                    "AA999ZZ",
                    "Toyota",
                    "Etios",
                    2022,
                    "CHASIS-999",
                    seg
            );

            vehiculoDao.crear(nuevo, conn);
            System.out.println("Vehículo creado con id: " + nuevo.getId());

            // 3) Buscar por id
            Vehiculo vPorId = vehiculoDao.obtenerPorId(nuevo.getId(), conn);
            System.out.println("Obtenido por id: " + vPorId);

            // 4) Buscar por dominio
            Vehiculo vPorDominio = vehiculoDao.obtenerPorDominio("AA999ZZ", conn);
            System.out.println("Obtenido por dominio: " + vPorDominio);

            // 5) Listar todos
            List<Vehiculo> lista = vehiculoDao.obtenerTodos(conn);
            System.out.println("Listado de vehículos:");
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
