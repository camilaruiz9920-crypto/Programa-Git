package com.adidas.tienda.model;

import java.util.Date;

/**
 * Clase que representa la entidad Pedido.
 */
public class Pedido {
    private int idPedido;
    private int idUsuario;
    private int idMetodoPago;
    private int idDireccion;
    private Date fechaPedido;
    private String estadoPedido; // pendiente, pagado, enviado, entregado, cancelado
    private double total;

    public Pedido() {}

    public Pedido(int idPedido, int idUsuario, int idMetodoPago, int idDireccion, Date fechaPedido, String estadoPedido, double total) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idMetodoPago = idMetodoPago;
        this.idDireccion = idDireccion;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.total = total;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdMetodoPago() { return idMetodoPago; }
    public void setIdMetodoPago(int idMetodoPago) { this.idMetodoPago = idMetodoPago; }

    public int getIdDireccion() { return idDireccion; }
    public void setIdDireccion(int idDireccion) { this.idDireccion = idDireccion; }

    public Date getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(Date fechaPedido) { this.fechaPedido = fechaPedido; }

    public String getEstadoPedido() { return estadoPedido; }
    public void setEstadoPedido(String estadoPedido) { this.estadoPedido = estadoPedido; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Pedido [ID=" + idPedido + ", Estado=" + estadoPedido + ", Total=" + total + "]";
    }
}
