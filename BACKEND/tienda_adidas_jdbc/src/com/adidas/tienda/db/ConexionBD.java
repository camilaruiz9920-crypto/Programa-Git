package com.adidas.tienda.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión a la base de datos MySQL.
 * 
 * @author Jose Narvaez
 */
public class ConexionBD {

    // Constantes de configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tienda_adidas?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // Cambiar según configuración local
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Establece y retorna una conexión con MySQL.
     * 
     * @return Connection objeto de conexión.
     * @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        Connection conexion = null;
        try {
            // Registro del driver JDBC
            Class.forName(DRIVER);
            // Obtención de la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado. " + e.getMessage());
        }
        return conexion;
    }
}
