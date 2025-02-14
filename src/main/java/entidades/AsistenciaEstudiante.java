package entidades;

import java.sql.Date;
import java.time.LocalDate;

import entidades.Estudiante;

public class AsistenciaEstudiante  {
	
	 private Estudiante estudiante; // Relación con Estudiante
	 
	    private Horario horario; // Relación con Horario
	    private Curso curso; // Relación con Curso
	
    private int asistenciaID;
    private int estudianteID;
    private int horarioID;
    private String estadoAsistencia;
    
    private String comentario;
    
    private Date fechaDeClase;
    private LocalDate fechaPrueba;
    
    public LocalDate getFechaPrueba() {
		return fechaPrueba;
	}



	public void setFechaPrueba(LocalDate fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
	}



	private String diaAsistencia;
    private String usuarioRegistro;
    private Date fechaActualizacion;





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



	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



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
