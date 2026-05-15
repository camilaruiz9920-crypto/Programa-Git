package com.adidas.tienda.model;

/**
 * Clase que representa la entidad Direccion.
 */
public class Direccion {
    private int idDireccion;
    private int idUsuario;
    private String direccionCompleta;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private boolean principal;

    public Direccion() {}

    public Direccion(int idDireccion, int idUsuario, String direccionCompleta, String ciudad, String departamento, String codigoPostal, boolean principal) {
        this.idDireccion = idDireccion;
        this.idUsuario = idUsuario;
        this.direccionCompleta = direccionCompleta;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.principal = principal;
    }

    public int getIdDireccion() { return idDireccion; }
    public void setIdDireccion(int idDireccion) { this.idDireccion = idDireccion; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getDireccionCompleta() { return direccionCompleta; }
    public void setDireccionCompleta(String direccionCompleta) { this.direccionCompleta = direccionCompleta; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public boolean isPrincipal() { return principal; }
    public void setPrincipal(boolean principal) { this.principal = principal; }

    @Override
    public String toString() {
        return "Direccion [ID=" + idDireccion + ", Ciudad=" + ciudad + ", Direccion=" + direccionCompleta + "]";
    }
}
