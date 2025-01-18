package entidades;

import java.util.Date;

public class Seccion {
    private int seccionID;
    private String nombreSeccion;
    private String numeroAula;
    private String estado;
    private Date fechaRegistro;
    private String usuarioRegistro;
    private Date fechaActualizacion;

    // Constructor vacío
    public Seccion() {}

    // Constructor completo
    public Seccion(int seccionID, String nombreSeccion, String numeroAula, String estado, 
                   Date fechaRegistro, String usuarioRegistro, Date fechaActualizacion) {
        this.seccionID = seccionID;
        this.nombreSeccion = nombreSeccion;
        this.numeroAula = numeroAula;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.usuarioRegistro = usuarioRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public int getSeccionID() {
        return seccionID;
    }

    public void setSeccionID(int seccionID) {
        this.seccionID = seccionID;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = numeroAula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}