package com.adidas.tienda.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de gestionar la conexión a la base de datos MySQL.
 * 
 * @author Jose Narvaez
 */
public class ConexionBD {

    private static String url;
    private static String usuario;
    private static String password;
    private static String driver;

    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/tienda_adidas?serverTimezone=UTC");
            usuario = properties.getProperty("db.user", "root");
            password = properties.getProperty("db.password", "admin");
            driver = properties.getProperty("db.driver", "com.mysql.cj.jdbc.Driver");
        } catch (IOException e) {
            System.err.println("Advertencia: No se pudo cargar config.properties, usando valores por defecto. " + e.getMessage());
            url = "jdbc:mysql://localhost:3306/tienda_adidas?serverTimezone=UTC";
            usuario = "root";
            password = "admin";
            driver = "com.mysql.cj.jdbc.Driver";
        }
    }

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
            Class.forName(driver);
            // Obtención de la conexión
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado. " + e.getMessage());
        }
        return conexion;
    }
}
