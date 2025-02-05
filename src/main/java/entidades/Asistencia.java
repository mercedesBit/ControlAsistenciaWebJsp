package entidades;

import java.sql.Date;
import java.sql.Timestamp;
//ATRIBUTOS QUE SE JALA DE LAS TABLAS  BD:tabla transaccional 
public class Asistencia {
	
	    private int asistenciaID;
	    private Estudiante estudiante;
	    private Curso curso;
	    private PersonalAdministrativo personal;
	    private Timestamp horaAsistencia;
	    private String estado;
	    private String comentario;
	    private String tipoAsistencia;
	    private Date fechaRegistro;
	    private String usuarioRegistro;
	    private Date fechaActualizacion;
	    
	    private int profesorID;

	    public int getAsistenciaID() {
	        return asistenciaID;
	    }

	    public void setAsistenciaID(int asistenciaID) {
	        this.asistenciaID = asistenciaID;
	    }

	    public Estudiante getEstudiante() {
	        return estudiante;
	    }

	    public void setEstudiante(Estudiante estudiante) {
	        this.estudiante = estudiante;
	    }

	    public Curso getCurso() {
	        return curso;
	    }

	    public void setCurso(Curso curso) {
	        this.curso = curso;
	    }

	    public PersonalAdministrativo getPersonal() {
	        return personal;
	    }

	    public void setPersonal(PersonalAdministrativo personal) {
	        this.personal = personal;
	    }

	  
	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    public String getComentario() {
	        return comentario;
	    }

	    public void setComentario(String comentario) {
	        this.comentario = comentario;
	    }

	    public String getTipoAsistencia() {
	        return tipoAsistencia;
	    }

	    public void setTipoAsistencia(String tipoAsistencia) {
	        this.tipoAsistencia = tipoAsistencia;
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

		public Timestamp getHoraAsistencia() {
			return horaAsistencia;
		}

		public void setHoraAsistencia(Timestamp horaAsistencia) {
			this.horaAsistencia = horaAsistencia;
		}

		public int getProfesorID() {
			return profesorID;
		}

		public void setProfesorID(int profesorID) {
			this.profesorID = profesorID;
		}
}
