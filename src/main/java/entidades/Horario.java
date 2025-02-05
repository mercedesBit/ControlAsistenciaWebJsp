package entidades;

import java.sql.Date;
import java.sql.Timestamp;

public class Horario {
    private int horarioID;
    private int cursoID;
    private int profesorID;
    private int seccionID;
    private String nombreCurso;
    private String nombreProfesor;
    private String apellidoProfesor;
    private String nombreSeccion;
    private String diaSemana;
    private String horaInicioFin;
    private Date fechaInicio;
    private Date fechaFin;
    private int maxEstudiantes;
    private String modalidad;
    private String estado;
    private Timestamp fechaRegistro;
    private String usuarioRegistro;
    private Timestamp fechaActualizacion;
    
    private Curso curso;
    private Profesor profesor;
    private Seccion seccion;

    // Constructor
	public Horario() {
		super();
	}

	public Horario(int horarioID, int cursoID, int profesorID, int seccionID, String nombreCurso, String nombreProfesor,
			String apellidoProfesor, String nombreSeccion, String diaSemana, String horaInicioFin, Date fechaInicio,
			Date fechaFin, int maxEstudiantes, String modalidad, String estado, Timestamp fechaRegistro,
			String usuarioRegistro, Timestamp fechaActualizacion) {
		super();
		this.horarioID = horarioID;
		this.cursoID = cursoID;
		this.profesorID = profesorID;
		this.seccionID = seccionID;
		this.nombreCurso = nombreCurso;
		this.nombreProfesor = nombreProfesor;
		this.apellidoProfesor = apellidoProfesor;
		this.nombreSeccion = nombreSeccion;
		this.diaSemana = diaSemana;
		this.horaInicioFin = horaInicioFin;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.maxEstudiantes = maxEstudiantes;
		this.modalidad = modalidad;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
		this.fechaActualizacion = fechaActualizacion;
		
	}

	public int getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(int horarioID) {
		this.horarioID = horarioID;
	}

	public int getCursoID() {
		return cursoID;
	}

	public void setCursoID(int cursoID) {
		this.cursoID = cursoID;
	}

	public int getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(int profesorID) {
		this.profesorID = profesorID;
	}

	public int getSeccionID() {
		return seccionID;
	}

	public void setSeccionID(int seccionID) {
		this.seccionID = seccionID;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicioFin() {
		return horaInicioFin;
	}

	public void setHoraInicioFin(String horaInicioFin) {
		this.horaInicioFin = horaInicioFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getMaxEstudiantes() {
		return maxEstudiantes;
	}

	public void setMaxEstudiantes(int maxEstudiantes) {
		this.maxEstudiantes = maxEstudiantes;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getApellidoProfesor() {
		return apellidoProfesor;
	}

	public void setApellidoProfesor(String apellidoProfesor) {
		this.apellidoProfesor = apellidoProfesor;
	}

	public String getNombreSeccion() {
		return nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	@Override
	public String toString() {
		return "Horario [horarioID=" + horarioID + ", cursoID=" + cursoID + ", profesorID=" + profesorID
				+ ", seccionID=" + seccionID + ", nombreCurso=" + nombreCurso + ", nombreProfesor=" + nombreProfesor
				+ ", apellidoProfesor=" + apellidoProfesor + ", nombreSeccion=" + nombreSeccion + ", diaSemana="
				+ diaSemana + ", horaInicioFin=" + horaInicioFin + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", maxEstudiantes=" + maxEstudiantes + ", modalidad=" + modalidad + ", estado=" + estado
				+ ", fechaRegistro=" + fechaRegistro + ", usuarioRegistro=" + usuarioRegistro + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}
}
