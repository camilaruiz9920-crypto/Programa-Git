package com.adidas.tienda.model;

/**
 * Clase que representa la entidad DetallePedido.
 */
public class DetallePedido {
    private int idDetalle;
    private int idPedido;
    private int idVariante;
    private int cantidad;
    private double precioUnitario;

    public DetallePedido() {}

    public DetallePedido(int idDetalle, int idPedido, int idVariante, int cantidad, double precioUnitario) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idVariante = idVariante;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdVariante() { return idVariante; }
    public void setIdVariante(int idVariante) { this.idVariante = idVariante; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    @Override
    public String toString() {
        return "DetallePedido [ID=" + idDetalle + ", Pedido=" + idPedido + ", Cantidad=" + cantidad + "]";
    }
}
