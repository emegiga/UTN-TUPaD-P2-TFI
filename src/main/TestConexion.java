/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author matut
 */
public class TestConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Intentamos obtener una conexión con try-with-resources para que se cierre sola
        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn != null) {
                System.out.println("✓ Conexión establecida con éxito.");

                // Sentencia SQL para seleccionar todos los productos
                String sql = "SELECT * FROM SeguroVehicular";

                // Preparar la sentencia para prevenir inyección SQL y optimizar ejecución
                try (PreparedStatement pstmt = conn.prepareStatement(sql); // Ejecutar la consulta y obtener el resultado
                         ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("Listado de Seguros:");

                    // Recorrer el ResultSet fila por fila mientras haya registros
                    while (rs.next()) {
                        // Obtener los campos id, nombre y precio de cada fila
                        int id = rs.getInt("id");
                        String aseguradora = rs.getString("aseguradora");
                        String nroPoliza = rs.getString("nroPoliza");
                        String cobertura = rs.getString("cobertura");
                        LocalDate vencimiento = rs.getObject("vencimiento", LocalDate.class); 

                        // Mostrar los datos por consola
                        System.out.println("ID " + id + "  Aseguradora: " + aseguradora + "  N° de póliza: " + nroPoliza + "  Cobertura: " + cobertura
                        + "  Vencimiento: " + vencimiento);
                    }
                }

            } else {
                // En caso que la conexión no se haya establecido correctamente
                System.out.println("X No se pudo establecer la conexión.");
            }

        } catch (SQLException e) {
            // Captura y muestra errores relacionados con la base de datos
            System.err.println("A Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
