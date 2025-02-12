package entidades;

import java.sql.Date;

public class AsistenciaEstudiante {
    private int asistenciaID;
    private int estudianteID;
    private int horarioID;
    private String estadoAsistencia;
    private String comentario;
    private Date fechaDeClase;
    private String diaAsistencia;
    private String usuarioRegistro;
    private Date fechaActualizacion;

    // Getters and Setters
    public int getAsistenciaID() {
        return asistenciaID;
    }

    public void setAsistenciaID(int asistenciaID) {
        this.asistenciaID = asistenciaID;
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public int getHorarioID() {
        return horarioID;
    }

    public void setHorarioID(int horarioID) {
        this.horarioID = horarioID;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaDeClase() {
        return fechaDeClase;
    }

    public void setFechaDeClase(Date fechaDeClase) {
        this.fechaDeClase = fechaDeClase;
    }

    public String getDiaAsistencia() {
        return diaAsistencia;
    }

    public void setDiaAsistencia(String diaAsistencia) {
        this.diaAsistencia = diaAsistencia;
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
