package entidades;

import java.sql.Date;
////ATRIBUTOS QUE SE JALA DE LAS TABLAS  BD
public class Profesor {
	    private int profesorID;

	    private String tipoDocumento;
	    private String numeroDocumento;
	    private String nombres;
	    private String apellidos;
	    private Date fechaNacimiento;
	    private String correoInstitucional;
	    private String telefonoMovil;
	    private String telefonoTrabajo;
	    private Date fechaContratacion;
	    private String especialidad;
	    private String gradoAcademico;
	    private int horasDocencia;
	    private String estado;
	    private String direccion;
	    private String modalidadTrabajo;
	    private String redesSociales;
	    private Date fechaRegistro;
	    private String usuarioRegistro;
	    private Date fechaActualizacion;

	    public int getProfesorID() {
	        return profesorID;
	    }

	    public void setProfesorID(int profesorID) {
	        this.profesorID = profesorID;
	    }

	    public String getTipoDocumento() {
	        return tipoDocumento;
	    }

	    public void setTipoDocumento(String tipoDocumento) {
	        this.tipoDocumento = tipoDocumento;
	    }

	    public String getNumeroDocumento() {
	        return numeroDocumento;
	    }

	    public void setNumeroDocumento(String numeroDocumento) {
	        this.numeroDocumento = numeroDocumento;
	    }

	    public String getNombres() {
	        return nombres;
	    }

	    public void setNombres(String nombres) {
	        this.nombres = nombres;
	    }

	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }

	    public Date getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public void setFechaNacimiento(Date fechaNacimiento) {
	        this.fechaNacimiento = fechaNacimiento;
	    }

	    public String getCorreoInstitucional() {
	        return correoInstitucional;
	    }

	    public void setCorreoInstitucional(String correoInstitucional) {
	        this.correoInstitucional = correoInstitucional;
	    }

	    public String getTelefonoMovil() {
	        return telefonoMovil;
	    }

	    public void setTelefonoMovil(String telefonoMovil) {
	        this.telefonoMovil = telefonoMovil;
	    }

	    public String getTelefonoTrabajo() {
	        return telefonoTrabajo;
	    }

	    public void setTelefonoTrabajo(String telefonoTrabajo) {
	        this.telefonoTrabajo = telefonoTrabajo;
	    }

	    public Date getFechaContratacion() {
	        return fechaContratacion;
	    }

	    public void setFechaContratacion(Date fechaContratacion) {
	        this.fechaContratacion = fechaContratacion;
	    }

	    public String getEspecialidad() {
	        return especialidad;
	    }

	    public void setEspecialidad(String especialidad) {
	        this.especialidad = especialidad;
	    }

	    public String getGradoAcademico() {
	        return gradoAcademico;
	    }

	    public void setGradoAcademico(String gradoAcademico) {
	        this.gradoAcademico = gradoAcademico;
	    }

	    public int getHorasDocencia() {
	        return horasDocencia;
	    }

	    public void setHorasDocencia(int horasDocencia) {
	        this.horasDocencia = horasDocencia;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public String getModalidadTrabajo() {
	        return modalidadTrabajo;
	    }

	    public void setModalidadTrabajo(String modalidadTrabajo) {
	        this.modalidadTrabajo = modalidadTrabajo;
	    }

	    public String getRedesSociales() {
	        return redesSociales;
	    }

	    public void setRedesSociales(String redesSociales) {
	        this.redesSociales = redesSociales;
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
