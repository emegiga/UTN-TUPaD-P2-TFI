/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matut
 */
public class DatabaseConnection {
    // URL de conexión a la base de datos MySQL a la base
    private static final String URL = "jdbc:mysql://localhost:3306/tfi_grupo147_v2";
    private static final String USER = "root";
    // Contraseña del usuario
    private static final String PASSWORD = "eLVISYRANTES1";

    // Bloque estático para cargar el driver JDBC una sola vez al iniciar la clase
    static {
        try {
            // Se registra el driver MySQL en el DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Si no se encuentra el driver, lanza excepción en tiempo de ejecución
            throw new RuntimeException("Error: No se encontró el driver JDBC, ", e);
        }
    }

    // Método estático que devuelve una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Validación para evitar URLs o credenciales vacías
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Configuración de la base de datos incompleta o inválida");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }    
}
