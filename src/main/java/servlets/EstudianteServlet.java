package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Estudiante;
import entidades.PadresTutores;
import modelo.EstudianteModel;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/EstudianteServlet")
public class EstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstudianteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        switch(tipo) {
        case "list" : listEstudiante(request, response); break;
        case "regist" : registEstudiante(request, response); break;
        case "info": detalleEstudiante(request, response); break;
        case "modif": modificarEstudiante(request, response); break;
        case "edit": actualizarEstudiante(request, response); break;
        case "delete": eliminarEstudiante(request, response); break;        
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("estudiante/regEstudiante.jsp").forward(request, response);
        }    
    }

    protected void listEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EstudianteModel model = new EstudianteModel();
        
        List<Estudiante> lista = model.listEstudiante();
        
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("estudiante/listEstudiante.jsp").forward(request, response);
    }

    protected void registEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String genero = request.getParameter("txtGenero");
        String email = request.getParameter("txtEmail");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String estadoCivil = request.getParameter("txtEstadoCivil");
        String fechaIngreso = request.getParameter("txtFechaIngreso");
        String estado = request.getParameter("txtEstado");
        String redesSociales = request.getParameter("txtRedesSociales");
        String emergenciaContacto = request.getParameter("txtEmergenciaContacto");
        int tutorID = Integer.parseInt(request.getParameter("txtTutorID"));
        
        Estudiante estudiante = new Estudiante();
        estudiante.setTipoDocumento(tipoDocumento);
        estudiante.setNumeroDocumento(numeroDocumento);
        estudiante.setNombres(nombres);
        estudiante.setApellidos(apellidos);
        estudiante.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        estudiante.setGenero(genero);
        estudiante.setEmail(email);
        estudiante.setTelefono(telefono);
        estudiante.setDireccion(direccion);
        estudiante.setEstadoCivil(estadoCivil);
        estudiante.setFechaIngreso(Date.valueOf(fechaIngreso));
        estudiante.setEstado(estado);
        estudiante.setRedesSociales(redesSociales);
        estudiante.setEmergenciaContacto(emergenciaContacto);
        // Asignar el tutor
        PadresTutores tutor = new PadresTutores();
        tutor.setTutorID(tutorID);
        estudiante.setTutor(tutor);
        estudiante.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));
        
        EstudianteModel model = new EstudianteModel();
        int value = model.registrarEstudiante(estudiante);
        
        if(value == 1) {
            listEstudiante(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("estudiante/regEstudiante.jsp").forward(request, response);
        }
    }

    protected void detalleEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("id"));
        
        EstudianteModel model = new EstudianteModel();
        
        Estudiante estudiante = model.obtenerEstudiante(idEstudiante);
        
        request.setAttribute("estudianteData", estudiante);
        request.getRequestDispatcher("estudiante/detEstudiante.jsp").forward(request, response);
    }

    protected void modificarEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("id"));
        
        EstudianteModel model = new EstudianteModel();
        
        Estudiante estudiante = model.obtenerEstudiante(idEstudiante);
        
        request.setAttribute("estudianteData", estudiante);
        request.getRequestDispatcher("estudiante/editEstudiante.jsp").forward(request, response);        
    }

    protected void actualizarEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idEstudiante"));
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String genero = request.getParameter("txtGenero");
        String email = request.getParameter("txtEmail");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String estadoCivil = request.getParameter("txtEstadoCivil");
        String fechaIngreso = request.getParameter("txtFechaIngreso");
        String estado = request.getParameter("txtEstado");
        String redesSociales = request.getParameter("txtRedesSociales");
        String emergenciaContacto = request.getParameter("txtEmergenciaContacto");
        int tutorID = Integer.parseInt(request.getParameter("txtTutorID"));
        
        Estudiante estudiante = new Estudiante();
        estudiante.setEstudianteID(id);
        estudiante.setTipoDocumento(tipoDocumento);
        estudiante.setNumeroDocumento(numeroDocumento);
        estudiante.setNombres(nombres);
        estudiante.setApellidos(apellidos);
        estudiante.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        estudiante.setGenero(genero);
        estudiante.setEmail(email);
        estudiante.setTelefono(telefono);
        estudiante.setDireccion(direccion);
        estudiante.setEstadoCivil(estadoCivil);
        estudiante.setFechaIngreso(Date.valueOf(fechaIngreso));
        estudiante.setEstado(estado);
        estudiante.setRedesSociales(redesSociales);
        estudiante.setEmergenciaContacto(emergenciaContacto);
        // Asignar el tutor
        PadresTutores tutor = new PadresTutores();
        tutor.setTutorID(tutorID);
        estudiante.setTutor(tutor);
        
        EstudianteModel model = new EstudianteModel();
        int value = model.editarEstudiante(estudiante);
        
        if(value == 1) {
            listEstudiante(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("estudiante/listEstudiante.jsp").forward(request, response);
        }        
    }

    protected void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EstudianteModel model = new EstudianteModel();
        int value = model.eliminarEstudiante(id);
        
        if(value == 1) {
            listEstudiante(request, response);             
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("estudiante/listEstudiante.jsp").forward(request, response);         
        }
    }

	

}
