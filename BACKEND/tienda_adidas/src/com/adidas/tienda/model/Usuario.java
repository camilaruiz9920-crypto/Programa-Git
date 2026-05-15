package com.adidas.tienda.model;

import java.util.Date;

/**
 * Clase que representa la entidad Usuario.
 */
public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String email;
    private String contrasenaHash;
    private String telefono;
    private Date fechaRegistro;
    private boolean activo;

    public Usuario() {}

    public Usuario(int idUsuario, String nombreCompleto, String email, String contrasenaHash, String telefono, Date fechaRegistro, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasenaHash = contrasenaHash;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", Nombre=" + nombreCompleto + ", Email=" + email + "]";
    }
}
