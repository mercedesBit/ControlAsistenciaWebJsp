package entidades;

import java.sql.Date;

public class PadresTutores {
	
	  private int tutorID;
	    private String tipoDocumento;
	    private String numeroDocumento;
	    private String nombres;
	    private String apellidos;
	    private String correoElectronico;
	    private String telefonoMovil;
	    private String telefonoCasa;
	    private String direccion;
	    private String relacionEstudiante;
	    private Date fechaNacimiento;
	    private String ocupacion;
	    private String estado;
	    private String redesSociales;
	    private Date fechaRegistro;
	    private String usuarioRegistro;
	    private Date fechaActualizacion;


	    public int getTutorID() {
	        return tutorID;
	    }

	    public void setTutorID(int tutorID) {
	        this.tutorID = tutorID;
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

	    public String getCorreoElectronico() {
	        return correoElectronico;
	    }

	    public void setCorreoElectronico(String correoElectronico) {
	        this.correoElectronico = correoElectronico;
	    }

	    public String getTelefonoMovil() {
	        return telefonoMovil;
	    }

	    public void setTelefonoMovil(String telefonoMovil) {
	        this.telefonoMovil = telefonoMovil;
	    }

	    public String getTelefonoCasa() {
	        return telefonoCasa;
	    }

	    public void setTelefonoCasa(String telefonoCasa) {
	        this.telefonoCasa = telefonoCasa;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public String getRelacionEstudiante() {
	        return relacionEstudiante;
	    }

	    public void setRelacionEstudiante(String relacionEstudiante) {
	        this.relacionEstudiante = relacionEstudiante;
	    }

	    public Date getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public void setFechaNacimiento(Date fechaNacimiento) {
	        this.fechaNacimiento = fechaNacimiento;
	    }

	    public String getOcupacion() {
	        return ocupacion;
	    }

	    public void setOcupacion(String ocupacion) {
	        this.ocupacion = ocupacion;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
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
