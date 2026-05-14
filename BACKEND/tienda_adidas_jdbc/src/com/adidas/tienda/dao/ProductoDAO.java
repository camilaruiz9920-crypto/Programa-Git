package com.adidas.tienda.dao;

import com.adidas.tienda.db.ConexionBD;
import com.adidas.tienda.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para realizar operaciones CRUD sobre la tabla Producto.
 */
public class ProductoDAO {

    /**
     * Inserta un nuevo producto en la base de datos.
     * @param p El objeto producto a insertar.
     * @return true si la inserción fue exitosa.
     */
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los productos registrados.
     * @return Lista de objetos Producto.
     */
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(extraerProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca un producto por su ID.
     * @param id El identificador del producto.
     * @return El objeto Producto encontrado o null.
     */
    public Producto buscarPorId(int id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return extraerProducto(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de un producto existente.
     * @param p El objeto producto con los nuevos datos.
     * @return true si la actualización fue exitosa.
     */
    public boolean actualizar(Producto p) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, categoria=? WHERE id_producto=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            ps.setInt(6, p.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un producto por su ID.
     * @param id El identificador del producto.
     * @return true si la eliminación fue exitosa.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper para mapear ResultSet a objeto Producto.
     */
    private Producto extraerProducto(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getDouble("precio"),
            rs.getInt("stock"),
            rs.getString("categoria")
        );
    }
}
