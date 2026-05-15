package com.adidas.tienda.model;

/**
 * Clase que representa la entidad MetodoPago.
 */
public class MetodoPago {
    private int idMetodoPago;
    private String nombreMetodo;
    private String descripcion;

    public MetodoPago() {}

    public MetodoPago(int idMetodoPago, String nombreMetodo, String descripcion) {
        this.idMetodoPago = idMetodoPago;
        this.nombreMetodo = nombreMetodo;
        this.descripcion = descripcion;
    }

    public int getIdMetodoPago() { return idMetodoPago; }
    public void setIdMetodoPago(int idMetodoPago) { this.idMetodoPago = idMetodoPago; }

    public String getNombreMetodo() { return nombreMetodo; }
    public void setNombreMetodo(String nombreMetodo) { this.nombreMetodo = nombreMetodo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "MetodoPago [ID=" + idMetodoPago + ", Nombre=" + nombreMetodo + "]";
    }
}
