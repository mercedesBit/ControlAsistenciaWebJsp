package entidades;

import java.sql.Date;

public class Matricula {

	    private String codigoMatricula;
	    private Estudiante estudiante;
	    private Horario horario;
	    private Date fechaMatricula;
	    private String estadoMatricula;
	    private String observaciones;
	    private String modoMatricula;
	    private String ciclo;

	    // Getters and Setters

	    public String getCodigoMatricula() {
	        return codigoMatricula;
	    }

	    public void setCodigoMatricula(String codigoMatricula) {
	        this.codigoMatricula = codigoMatricula;
	    }

	    public Estudiante getEstudiante() {
	        return estudiante;
	    }

	    public void setEstudiante(Estudiante estudiante) {
	        this.estudiante = estudiante;
	    }

	    public Horario getHorario() {
	        return horario;
	    }

	    public void setHorario(Horario horario) {
	        this.horario = horario;
	    }

	    public Date getFechaMatricula() {
	        return fechaMatricula;
	    }

	    public void setFechaMatricula(Date fechaMatricula) {
	        this.fechaMatricula = fechaMatricula;
	    }

	    public String getEstadoMatricula() {
	        return estadoMatricula;
	    }

	    public void setEstadoMatricula(String estadoMatricula) {
	        this.estadoMatricula = estadoMatricula;
	    }


	    public String getObservaciones() {
	        return observaciones;
	    }

	    public void setObservaciones(String observaciones) {
	        this.observaciones = observaciones;
	    }

	    public String getModoMatricula() {
	        return modoMatricula;
	    }

	    public void setModoMatricula(String modoMatricula) {
	        this.modoMatricula = modoMatricula;
	    }

	    public String getCiclo() {
	        return ciclo;
	    }

	    public void setCiclo(String ciclo) {
	        this.ciclo = ciclo;
	    }


}
