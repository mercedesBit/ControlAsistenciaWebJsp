package entidades;

import java.util.Date;

public class Curso {
    private int cursoID;
    private String codigoCurso;
    private String nombreCurso;
    private String descripcion;
    private int duracion;
    private String grado;
    private String nivel;
    //private Date fechaInicio;
    //private Date fechaFin;
    private String estado;
    private String requisitosPrevios;
    private int cantidadMaximaEstudiantes;
    private String modalidad;
    //private int seccionID;
    //private String temario;
    //private String horario;
    private String notas;
   // private int profesorID;
    private Date fechaRegistro;
    private String usuarioRegistro;
    private Date fechaActualizacion;

    // Constructor vacío
    public Curso() {}

    // Constructor completo
    public Curso(int cursoID, String codigoCurso, String nombreCurso, String descripcion, int duracion, 
            String grado, String nivel, /* Date fechaInicio, Date fechaFin, */ String estado, 
            String requisitosPrevios, int cantidadMaximaEstudiantes, String modalidad, 
            /* int seccionID, String temario, String horario, */ String notas, 
            /* int profesorID, */ Date fechaRegistro, String usuarioRegistro, Date fechaActualizacion) {
   this.cursoID = cursoID;
   this.codigoCurso = codigoCurso;
   this.nombreCurso = nombreCurso;
   this.descripcion = descripcion;
   this.duracion = duracion;
   this.grado = grado;
   this.nivel = nivel;
   // this.fechaInicio = fechaInicio; // Comentado porque no se usa en la lógica actual
   // this.fechaFin = fechaFin;       // Comentado porque no se usa en la lógica actual
   this.estado = estado;
   this.requisitosPrevios = requisitosPrevios;
   this.cantidadMaximaEstudiantes = cantidadMaximaEstudiantes;
   this.modalidad = modalidad;
   // this.seccionID = seccionID;     // Comentado porque no se usa en la lógica actual
   // this.temario = temario;         // Comentado porque no se usa en la lógica actual
   // this.horario = horario;         // Comentado porque no se usa en la lógica actual
   this.notas = notas;
   // this.profesorID = profesorID;   // Comentado porque no se usa en la lógica actual
   this.fechaRegistro = fechaRegistro;
   this.usuarioRegistro = usuarioRegistro;
   this.fechaActualizacion = fechaActualizacion;
}


    // Getters y Setters
    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

  /*  public Date getFechaInicio() {
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
*/ 
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRequisitosPrevios() {
        return requisitosPrevios;
    }

    public void setRequisitosPrevios(String requisitosPrevios) {
        this.requisitosPrevios = requisitosPrevios;
    }

    public int getCantidadMaximaEstudiantes() {
        return cantidadMaximaEstudiantes;
    }

    public void setCantidadMaximaEstudiantes(int cantidadMaximaEstudiantes) {
        this.cantidadMaximaEstudiantes = cantidadMaximaEstudiantes;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /*
    public int getSeccionID() {
        return seccionID;
    }

    public void setSeccionID(int seccionID) {
        this.seccionID = seccionID;
    }

    public String getTemario() {
        return temario;
    }

    public void setTemario(String temario) {
        this.temario = temario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
    
        public int getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }
    */
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
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