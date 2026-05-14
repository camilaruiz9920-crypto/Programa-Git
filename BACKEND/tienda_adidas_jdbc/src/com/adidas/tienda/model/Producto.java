package com.adidas.tienda.model;

/**
 * Clase que representa la entidad Producto en el sistema Adidas Colombia.
 * 
 * @author Jose Narvaez
 */
public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String categoria;

    /**
     * Constructor vacío.
     */
    public Producto() {
    }

    /**
     * Constructor con todos los parámetros.
     * 
     * @param idProducto Identificador único.
     * @param nombre Nombre del producto.
     * @param descripcion Detalle del producto.
     * @param precio Valor unitario.
     * @param stock Cantidad disponible.
     * @param categoria Tipo de producto (Calzado, Ropa, etc.)
     */
    public Producto(int idProducto, String nombre, String descripcion, double precio, int stock, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Producto [" +
                "ID=" + idProducto +
                ", Nombre='" + nombre + '\'' +
                ", Precio=$" + precio +
                ", Stock=" + stock +
                ", Categoría='" + categoria + '\'' +
                ']';
    }
}
