package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Horario;
import modelo.HorarioModel;

/**
 * Servlet implementation class HorarioServlet
 */
@WebServlet("/HorarioServlet")
public class HorarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
        
        switch(tipo) {
        case "list" : listHorario(request, response); break;
        case "regist" : registHorario(request, response); break;
        case "info": detalleHorario(request, response); break;
        case "modif": modificarHorario(request, response); break;
        case "edit": actualizarHorario(request, response); break;
        case "delete": eliminarHorario(request, response); break;      
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("horario/listHorario.jsp").forward(request, response);
        }   
	}

	private void listHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HorarioModel model = new HorarioModel();
        List<Horario> lista = model.listHorario();
        
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("horario/listHorario.jsp").forward(request, response);	
		
	}

	private void registHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cursoID = Integer.parseInt(request.getParameter("txtCursoID"));
        int profesorID = Integer.parseInt(request.getParameter("txtProfesorID"));
        int seccionID = Integer.parseInt(request.getParameter("txtSeccionID"));
        String diaSemana = request.getParameter("txtDiaSemana");
        String horaInicioFin = request.getParameter("txtHoraInicioFin");
        Date fechaInicio = Date.valueOf(request.getParameter("txtFechaInicio"));
        Date fechaFin = Date.valueOf(request.getParameter("txtFechaFin"));
        int maxEstudiantes = Integer.parseInt(request.getParameter("txtMaxEstudiantes"));
        String modalidad = request.getParameter("txtModalidad");
        String estado = request.getParameter("txtEstado");

        Horario horario = new Horario();
        horario.setCursoID(cursoID);
        horario.setProfesorID(profesorID);
        horario.setSeccionID(seccionID);
        horario.setDiaSemana(diaSemana);
        horario.setHoraInicioFin(horaInicioFin);
        horario.setFechaInicio(fechaInicio);
        horario.setFechaFin(fechaFin);
        horario.setMaxEstudiantes(maxEstudiantes);
        horario.setModalidad(modalidad);
        horario.setEstado(estado);
        horario.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));

        HorarioModel model = new HorarioModel();
        int value = model.registrarHorario(horario);
        
        if (value == 1) {
        	listHorario(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrió un problema al registrar el horario");
            request.getRequestDispatcher("horario/regHorario.jsp").forward(request, response);
        }
		
	}

	private void detalleHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idHorario = Integer.parseInt(request.getParameter("id"));
        
        HorarioModel model = new HorarioModel();
        
        Horario horario = model.obtenerHorario(idHorario);
        
        request.setAttribute("horarioData", horario);
        request.getRequestDispatcher("horario/detHorario.jsp").forward(request, response);
		
	}

	private void modificarHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idHorario = Integer.parseInt(request.getParameter("id"));
        
		HorarioModel model = new HorarioModel();
        
		Horario horario = model.obtenerHorario(idHorario);
        
        request.setAttribute("horarioData", horario);
        request.getRequestDispatcher("horario/editHorario.jsp").forward(request, response);   
		
	}

	private void actualizarHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int horarioID = Integer.parseInt(request.getParameter("idHorario"));
        int cursoID = Integer.parseInt(request.getParameter("txtCursoID"));
        int profesorID = Integer.parseInt(request.getParameter("txtProfesorID"));
        int seccionID = Integer.parseInt(request.getParameter("txtSeccionID"));
        String diaSemana = request.getParameter("txtDiaSemana");
        String horaInicioFin = request.getParameter("txtHoraInicioFin");
        Date fechaInicio = Date.valueOf(request.getParameter("txtFechaInicio"));
        Date fechaFin = Date.valueOf(request.getParameter("txtFechaFin"));
        int maxEstudiantes = Integer.parseInt(request.getParameter("txtMaxEstudiantes"));
        String modalidad = request.getParameter("txtModalidad");
        String estado = request.getParameter("txtEstado");
        
        Horario horario = new Horario();
        horario.setHorarioID(horarioID);
        horario.setCursoID(cursoID);
        horario.setProfesorID(profesorID);
        horario.setSeccionID(seccionID);
        horario.setDiaSemana(diaSemana);
        horario.setHoraInicioFin(horaInicioFin);
        horario.setFechaInicio(fechaInicio);
        horario.setFechaFin(fechaFin);
        horario.setMaxEstudiantes(maxEstudiantes);
        horario.setModalidad(modalidad);
        horario.setEstado(estado);
        horario.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));
        
        HorarioModel model = new HorarioModel();
        int value = model.editarHorario(horario);
        
        if (value == 1) {
        	listHorario(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrió un problema al actualizar el horario");
            request.getRequestDispatcher("horario/listHorario.jsp").forward(request, response);
        }
	}

	private void eliminarHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        HorarioModel model = new HorarioModel();
        int value = model.eliminarHorario(id);
        
        if(value == 1) {
            listHorario(request, response);             
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("horario/listHorario.jsp").forward(request, response);         
        }
	}
}
