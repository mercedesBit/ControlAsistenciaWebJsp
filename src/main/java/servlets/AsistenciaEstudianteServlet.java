package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.AsistenciaEstudiante;
import entidades.Estudiante;
import entidades.Horario;
import entidades.HorarioEstudiante;
import modelo.AsistenciaEstudianteModel;

import modelo.EstudianteModel;
import modelo.HorarioModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AsistenciaEstudianteServlet")
public class AsistenciaEstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AsistenciaEstudianteModel modelo = new AsistenciaEstudianteModel();

	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String tipo = request.getParameter("tipo");

	    // Validar si 'tipo' es null o vacío
	    if (tipo == null || tipo.trim().isEmpty()) {
	        tipo = "list"; // Puedes cambiar "default" por otro valor adecuado
	    }

	    switch (tipo) {
	        case "list":
	            listAsistencia(request, response);
	            break;
	            
	        case "listAsistenciaxAlumno":
	            listAsistenciaxAlumno(request, response);
	            break;
	        
	        default:
	            request.setAttribute("mensaje", "Ocurrió un problema: tipo no válido.");
	            request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	    }
	}


	public AsistenciaEstudianteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		modelo = new AsistenciaEstudianteModel();
	}



	private void listAsistencia(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HorarioModel modelHorario = new HorarioModel();
	    AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();

	    // Obtener el horarioID seleccionado del JSP
	    String horarioIDParam = request.getParameter("horarioID");

	    List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<HorarioEstudiante>(); // Evita null

	    if (horarioIDParam != null && !horarioIDParam.isEmpty()) {
	        try {
	            int horarioID = Integer.parseInt(horarioIDParam);
	            listaHorarioEstudiante = modelAsistenciaEstudiante.obtenerEstudiantesPorHorario(horarioID);
	        } catch (NumberFormatException e) {
	            request.setAttribute("mensaje", "Error: El ID del horario no es válido.");
	            request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	            return; // Detiene la ejecución para evitar errores.
	        }
	    }

	    // Obtener la lista de horarios (esto siempre debe cargarse)
	    List<Horario> listaHorario = modelHorario.listHorario();

	    request.setAttribute("listaHorario", listaHorario);
	    request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

	    request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	}


	
	
	
	private void listAsistenciaxAlumno(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String horarioIDParam = request.getParameter("horarioID");
	    String estudianteIDParam = request.getParameter("estudianteID");

	    if (horarioIDParam == null || horarioIDParam.isEmpty() || estudianteIDParam == null || estudianteIDParam.isEmpty()) {
	        request.setAttribute("mensaje", "Debe seleccionar un horario y un estudiante.");
	        request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	        return;
	    }

	    try {
	        int horarioID = Integer.parseInt(horarioIDParam);
	        int estudianteID = Integer.parseInt(estudianteIDParam);

	        AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();
	        List<AsistenciaEstudiante> listaAsistencia = modelAsistencia.obtenerAsistencia(estudianteID, horarioID);

	        request.setAttribute("listaAsistencia", listaAsistencia);
	    } catch (NumberFormatException e) {
	        request.setAttribute("mensaje", "Error: ID de horario o estudiante no válido.");
	    }

	    request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	}


	

	


}
