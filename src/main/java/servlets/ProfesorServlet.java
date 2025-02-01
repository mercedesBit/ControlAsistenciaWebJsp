package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entidades.Profesor;
import modelo.ProfesorModel;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/ProfesorServlet")
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfesorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        switch(tipo) {
        case "list" : listProfesor(request, response); break;
        case "regist" : registProfesor(request, response); break;
        case "info": detalleProfesor(request, response); break;
        case "modif": modificarProfesor(request, response); break;
        case "edit": actualizarProfesor(request, response); break;
        case "delete": eliminarProfesor(request, response); break;        
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("profesor/regProfesores.jsp").forward(request, response);
        }    
    }

    protected void listProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ProfesorModel model = new ProfesorModel();
        
        List<Profesor> lista = model.listProfesor();
        
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("profesor/listProfesores.jsp").forward(request, response);
    }

    protected void registProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String correoInstitucional = request.getParameter("txtCorreoInstitucional");
        String telefonoMovil = request.getParameter("txtTelefonoMovil");
        String telefonoTrabajo = request.getParameter("txtTelefonoTrabajo");
        String fechaContratacion = request.getParameter("txtFechaContratacion");
        String especialidad = request.getParameter("txtEspecialidad");
        String gradoAcademico = request.getParameter("txtGradoAcademico");
        int horasDocencia = Integer.parseInt(request.getParameter("txtHorasDocencia"));
        String estado = request.getParameter("txtEstado");
        String direccion = request.getParameter("txtDireccion");
        String modalidadTrabajo = request.getParameter("txtModalidadTrabajo");
        String redesSociales = request.getParameter("txtRedesSociales");
        
        Profesor profesor = new Profesor();
        profesor.setTipoDocumento(tipoDocumento);
        profesor.setNumeroDocumento(numeroDocumento);
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setTelefonoMovil(telefonoMovil);
        profesor.setTelefonoTrabajo(telefonoTrabajo);
        profesor.setFechaContratacion(Date.valueOf(fechaContratacion));
        profesor.setEspecialidad(especialidad);
        profesor.setGradoAcademico(gradoAcademico);
        profesor.setHorasDocencia(horasDocencia);
        profesor.setEstado(estado);
        profesor.setDireccion(direccion);
        profesor.setModalidadTrabajo(modalidadTrabajo);
        profesor.setRedesSociales(redesSociales);
        profesor.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));
        profesor.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));
        
        ProfesorModel model = new ProfesorModel();
        int value = model.registrarProfesor(profesor);
        
        if(value == 1) {
            listProfesor(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("profesor/regProfesores.jsp").forward(request, response);
        }
    }

    protected void detalleProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProfesor = Integer.parseInt(request.getParameter("id"));
        
        ProfesorModel model = new ProfesorModel();
        
        Profesor profesor = model.obtenerProfesor(idProfesor);
        
        request.setAttribute("profesorData", profesor);
        request.getRequestDispatcher("profesor/detProfesor.jsp").forward(request, response);
    }

    protected void modificarProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProfesor = Integer.parseInt(request.getParameter("id"));
        
        ProfesorModel model = new ProfesorModel();
        
        Profesor profesor = model.obtenerProfesor(idProfesor);
        
        request.setAttribute("profesorData", profesor);
        request.getRequestDispatcher("profesor/editProfesor.jsp").forward(request, response);        
    }

    protected void actualizarProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("profesorID"));
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String correoInstitucional = request.getParameter("txtCorreoInstitucional");
        String telefonoMovil = request.getParameter("txtTelefonoMovil");
        String telefonoTrabajo = request.getParameter("txtTelefonoTrabajo");
        String fechaContratacion = request.getParameter("txtFechaContratacion");
        String especialidad = request.getParameter("txtEspecialidad");
        String gradoAcademico = request.getParameter("txtGradoAcademico");
        int horasDocencia = Integer.parseInt(request.getParameter("txtHorasDocencia"));
        String estado = request.getParameter("txtEstado");
        String direccion = request.getParameter("txtDireccion");
        String modalidadTrabajo = request.getParameter("txtModalidadTrabajo");
        String redesSociales = request.getParameter("txtRedesSociales");
        String fechaRegistro = request.getParameter("txtFechaRegistro");
        String usuarioRegistro = request.getParameter("txtUsuarioRegistro");
        
        Profesor profesor = new Profesor();
        profesor.setProfesorID(id);
        profesor.setTipoDocumento(tipoDocumento);
        profesor.setNumeroDocumento(numeroDocumento);
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setTelefonoMovil(telefonoMovil);
        profesor.setTelefonoTrabajo(telefonoTrabajo);
        profesor.setFechaContratacion(Date.valueOf(fechaContratacion));
        profesor.setEspecialidad(especialidad);
        profesor.setGradoAcademico(gradoAcademico);
        profesor.setHorasDocencia(horasDocencia);
        profesor.setEstado(estado);
        profesor.setDireccion(direccion);
        profesor.setModalidadTrabajo(modalidadTrabajo);
        profesor.setRedesSociales(redesSociales);
        profesor.setFechaRegistro(Date.valueOf(fechaRegistro));
        profesor.setUsuarioRegistro(usuarioRegistro);
        
        ProfesorModel model = new ProfesorModel();
        int value = model.editarProfesor(profesor);
        
        if(value == 1) {
            listProfesor(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("profesor/listProfesores.jsp").forward(request, response);
        }        
    }

    protected void eliminarProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProfesorModel model = new ProfesorModel();
        int value = model.eliminarProfesor(id);
        
        if(value == 1) {
            listProfesor(request, response);             
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("profesor/listProfesores.jsp").forward(request, response);         
        }
    }

}
