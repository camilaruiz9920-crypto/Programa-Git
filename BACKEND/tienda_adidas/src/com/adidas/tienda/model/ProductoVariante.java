package com.adidas.tienda.model;

/**
 * Clase que representa la entidad ProductoVariante.
 */
public class ProductoVariante {
    private int idVariante;
    private int idProducto;
    private String talla;
    private String color;
    private int stock;
    private Double precioVenta; // Usar Double para permitir nulls si el precio es el base

    public ProductoVariante() {}

    public ProductoVariante(int idVariante, int idProducto, String talla, String color, int stock, Double precioVenta) {
        this.idVariante = idVariante;
        this.idProducto = idProducto;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.precioVenta = precioVenta;
    }

    public int getIdVariante() { return idVariante; }
    public void setIdVariante(int idVariante) { this.idVariante = idVariante; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(Double precioVenta) { this.precioVenta = precioVenta; }

    @Override
    public String toString() {
        return "Variante [ID=" + idVariante + ", Talla=" + talla + ", Color=" + color + ", Stock=" + stock + "]";
    }
}
