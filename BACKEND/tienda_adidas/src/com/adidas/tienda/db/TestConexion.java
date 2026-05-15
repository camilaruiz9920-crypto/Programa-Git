package com.adidas.tienda.db;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("Intentando conectar a la base de datos tienda_adidas...");
        try (Connection con = ConexionBD.getConnection()) {
            if (con != null) {
                System.out.println("¡CONEXIÓN EXITOSA!");
                System.out.println("Base de datos conectada: " + con.getMetaData().getDatabaseProductName());
            } else {
                System.out.println("Error: La conexión es nula.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXIÓN:");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("Código de error: " + e.getErrorCode());
            System.err.println("\nCONSEJOS:");
            System.err.println("1. Asegúrate de que MySQL Server esté iniciado.");
            System.err.println("2. Verifica que el usuario y contraseña en ConexionBD.java sean correctos.");
            System.err.println("3. Asegúrate de haber ejecutado el script SQL.");
        }
    }
}
