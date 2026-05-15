package com.adidas.tienda.model;

/**
 * Clase que representa la entidad Producto en el sistema Adidas Colombia.
 * 
 * @author Jose Narvaez
 */
public class Producto {

    private int idProducto;
    private String nombreProducto;
    private String descripcion;
    private double precioBase;
    private int idCategoria;

    /**
     * Constructor vacío.
     */
    public Producto() {
    }

    /**
     * Constructor con todos los parámetros.
     * 
     * @param idProducto Identificador único.
     * @param nombreProducto Nombre del producto.
     * @param descripcion Detalle del producto.
     * @param precioBase Valor base.
     * @param idCategoria ID de la categoría.
     */
    public Producto(int idProducto, String nombreProducto, String descripcion, double precioBase, int idCategoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.idCategoria = idCategoria;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Producto [" +
                "ID=" + idProducto +
                ", Nombre='" + nombreProducto + '\'' +
                ", Precio Base=$" + precioBase +
                ", ID Categoría=" + idCategoria +
                ']';
    }
}
