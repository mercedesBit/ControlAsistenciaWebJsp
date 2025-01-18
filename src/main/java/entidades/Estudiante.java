package entidades;

import java.sql.Date;

public class Estudiante {
	
	    private int estudianteID;

	    private String tipoDocumento;
	    private String numeroDocumento;
	    private String nombres;
	    private String apellidos;
	    private Date fechaNacimiento;
	    private String genero;
	    private String email;
	    private String telefono;
	    private String direccion;
	    private String estadoCivil;
	    private Date fechaIngreso;
	    private String estado;
	    private String redesSociales;
	    private String emergenciaContacto;

	    private PadresTutores tutor;

	    private Date fechaRegistro;
	    private String usuarioRegistro;
	    private Date fechaActualizacion;

	    public int getEstudianteID() {
	        return estudianteID;
	    }

	    public void setEstudianteID(int estudianteID) {
	        this.estudianteID = estudianteID;
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

	    public String getGenero() {
	        return genero;
	    }

	    public void setGenero(String genero) {
	        this.genero = genero;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public String getEstadoCivil() {
	        return estadoCivil;
	    }

	    public void setEstadoCivil(String estadoCivil) {
	        this.estadoCivil = estadoCivil;
	    }

	    public Date getFechaIngreso() {
	        return fechaIngreso;
	    }

	    public void setFechaIngreso(Date fechaIngreso) {
	        this.fechaIngreso = fechaIngreso;
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

	    public String getEmergenciaContacto() {
	        return emergenciaContacto;
	    }

	    public void setEmergenciaContacto(String emergenciaContacto) {
	        this.emergenciaContacto = emergenciaContacto;
	    }

	    public PadresTutores getTutor() {
	        return tutor;
	    }

	    public void setTutor(PadresTutores tutor) {
	        this.tutor = tutor;
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
