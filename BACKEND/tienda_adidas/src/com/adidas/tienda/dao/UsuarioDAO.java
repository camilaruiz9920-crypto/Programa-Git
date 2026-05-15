package com.adidas.tienda.dao;

import com.adidas.tienda.db.ConexionBD;
import com.adidas.tienda.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para realizar operaciones CRUD sobre la tabla USUARIO.
 */
public class UsuarioDAO {

    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO USUARIO (nombre_completo, email, contrasena_hash, telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombreCompleto());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getContrasenaHash());
            ps.setString(4, u.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(extraerUsuario(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM USUARIO WHERE email = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return extraerUsuario(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    private Usuario extraerUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"),
            rs.getString("nombre_completo"),
            rs.getString("email"),
            rs.getString("contrasena_hash"),
            rs.getString("telefono"),
            rs.getTimestamp("fecha_registro"),
            rs.getBoolean("activo")
        );
    }
}
