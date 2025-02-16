package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.AsistenciaEstudiante;
import entidades.Horario;
import entidades.HorarioEstudiante;
import modelo.AsistenciaEstudianteModel;
import modelo.HorarioModel;
import modelo.ReporteAsistenciaModel;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AsistenciaEstudianteServlet")
public class AsistenciaEstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		tipo = (tipo == null || tipo.trim().isEmpty()) ? "list" : tipo;

		switch (tipo) {
		case "list":
			listAsistencia(request, response);
			break;

		case "listAsistenciaxAlumno":
			listAsistenciaxAlumno(request, response);
			break;

		case "nuevo":
			mostrarFormularioNuevo(request, response);
			break;

		case "editar":
			mostrarFormularioEdicion(request, response);
			break;
		case "eliminar":
			eliminarAsistencia(request, response);
			break;

		case "detalle":
			mostrarDetalle(request, response);
			break;

		case "actualizar":
			actualizarAsistencia(request, response);
			break;

		case "registrar":
			registrarAsistencia(request, response);
			break;
			
		case "reporte":
			reporteAsistencia(request, response);
			break;
			
		case "reportelistAsistenciaxAlumno":
			reportelistAsistenciaxAlumno(request, response);
			break;
			
		case "generarReporte":
			generarReporteAsistencia(request, response);
			break;
		case "generarAutomatico":
			generarAutomatico(request, response);
			break;
			
		case "aprobar":
			aprobar(request, response);
			break;
		case "rechazar":
			rechazar(request, response);
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

	/*@Override
	public void init() throws ServletException {
		modelAsistencia = new AsistenciaEstudianteModel();
	}*/

	AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();
	
	
	LocalDate fechaActual = LocalDate.now();

	private void listAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HorarioModel modelHorario = new HorarioModel();
		// AsistenciaEstudianteModel modelAsistenciaEstudiante = new
		// AsistenciaEstudianteModel();

		// Obtener el horarioID seleccionado del JSP
		String horarioIDParam = request.getParameter("horarioID");

		List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<HorarioEstudiante>(); // Evita null

		if (horarioIDParam != null && !horarioIDParam.isEmpty()) {
			try {
				int horarioID = Integer.parseInt(horarioIDParam);
				listaHorarioEstudiante = modelAsistencia.obtenerEstudiantesPorHorario(horarioID);
			} catch (NumberFormatException e) {
				
				
				
				// Obtener la lista de horarios (esto siempre debe cargarse)
				List<Horario> listaHorario = modelHorario.listHorario();

				String IDestudiante = request.getParameter("estudianteID");

				request.setAttribute("listaHorario", listaHorario);
				request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

				System.out.println("📌 Consultando asistencia del estudiante:");
				System.out.println("Horario ID: " + horarioIDParam);
				System.out.println("Estudiante ID: " + IDestudiante);

				request.setAttribute("IDestudiante", IDestudiante);
				request.setAttribute("IDhorario", horarioIDParam);
				
				
				
				request.setAttribute("mensaje", "Error: El ID del horario no es válido.");
				request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
				return; // Detiene la ejecución para evitar errores.
			}
		}

		// Obtener la lista de horarios (esto siempre debe cargarse)
		List<Horario> listaHorario = modelHorario.listHorario();

		String IDestudiante = request.getParameter("estudianteID");

		request.setAttribute("listaHorario", listaHorario);
		request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

		System.out.println("📌 Consultando asistencia del estudiante:");
		System.out.println("Horario ID: " + horarioIDParam);
		System.out.println("Estudiante ID: " + IDestudiante);

		request.setAttribute("IDestudiante", IDestudiante);
		request.setAttribute("IDhorario", horarioIDParam);

		request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	}

	private void listAsistenciaxAlumno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HorarioModel modelHorario = new HorarioModel();
		String horarioIDParam = request.getParameter("horarioID");
		String estudianteIDParam = request.getParameter("estudianteID");

		HttpSession session = request.getSession();

		// Recuperar de la sesión si no hay parámetros en la URL
		if ((horarioIDParam == null || horarioIDParam.isEmpty()) && session.getAttribute("horarioID") != null) {
			horarioIDParam = session.getAttribute("horarioID").toString();
		}
		if ((estudianteIDParam == null || estudianteIDParam.isEmpty())
				&& session.getAttribute("estudianteID") != null) {
			estudianteIDParam = session.getAttribute("estudianteID").toString();
		}

		if (horarioIDParam == null || horarioIDParam.isEmpty()) {
			System.out.println("Error: horarioID es nulo o vacío.");
			List<Horario> listaHorario = modelHorario.listHorario();
			request.setAttribute("listaHorario", listaHorario);
			request.setAttribute("error", "Debe seleccionar un horario.");
			request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
			return;
		}

		List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<>();
		List<Horario> listaHorario = modelHorario.listHorario();
		request.setAttribute("listaHorario", listaHorario);

		try {
			int horarioID = Integer.parseInt(horarioIDParam);
			listaHorarioEstudiante = modelAsistencia.obtenerEstudiantesPorHorario(horarioID);
			request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

			List<AsistenciaEstudiante> listaAsistenciaEstudiante = new ArrayList<>();
			if (estudianteIDParam != null && !estudianteIDParam.isEmpty()) {
				AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();
				listaAsistenciaEstudiante = modelAsistencia.obtenerAsistencia(horarioIDParam, estudianteIDParam);
				request.setAttribute("listaAsistenciaEstudiante", listaAsistenciaEstudiante);
			}

			// Guardar en la sesión para futuras consultas
			session.setAttribute("horarioID", horarioIDParam);
			session.setAttribute("estudianteID", estudianteIDParam);

		} catch (NumberFormatException e) {
			System.out.println("Error: No se pudo convertir horarioID a número entero.");
			request.setAttribute("error", "Formato de ID inválido.");
			request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	}

	private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int horarioID = Integer.parseInt(request.getParameter("horarioID"));
		int EstudianteID = Integer.parseInt(request.getParameter("estudianteID"));
		

        HorarioModel model = new HorarioModel();
        Horario horario = model.obtenerHorario(horarioID);
        
        request.setAttribute("horarioData", horario);
        
     	request.setAttribute("horarioID", horarioID);
		request.setAttribute("EstudianteID", EstudianteID);
		request.getRequestDispatcher("asistencia/regAsistencia.jsp").forward(request, response);
	}

	protected void mostrarDetalle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int AsistenciaID = Integer.parseInt(request.getParameter("id"));

		AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();

		AsistenciaEstudiante asistenciaEstudiante = modelAsistenciaEstudiante.obtenerPorID(AsistenciaID);

		request.setAttribute("asistenciaEstudiante", asistenciaEstudiante);
		request.getRequestDispatcher("asistencia/detAsistencia.jsp").forward(request, response);
	}

	private void eliminarAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();
		HttpSession session = request.getSession();

		int asistenciaID = Integer.parseInt(request.getParameter("id"));
		int resultado = modelAsistenciaEstudiante.eliminar(asistenciaID);

		String horarioID = (session.getAttribute("horarioID") != null) ? session.getAttribute("horarioID").toString()
				: "";
		String estudianteID = (session.getAttribute("estudianteID") != null)
				? session.getAttribute("estudianteID").toString()
				: "";

		if (resultado > 0) {
			session.setAttribute("mensaje", "Asistencia eliminada correctamente.");
			response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=" + horarioID
					+ "&estudianteID=" + estudianteID);
		} else {
			session.setAttribute("error", "No se puede eliminar esta asistencia.");
			response.sendRedirect("AsistenciaEstudianteServlet?tipo=list");
		}
	}

	private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int asistenciID = Integer.parseInt(request.getParameter("id"));

		AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();
		AsistenciaEstudiante asistenciaEstudiante = modelAsistenciaEstudiante.obtenerPorID(asistenciID);

		request.setAttribute("asistenciaEstudiante", asistenciaEstudiante);
		request.getRequestDispatcher("asistencia/editAsistencia.jsp").forward(request, response);
	}

	private void actualizarAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();

		int asistenciaID = Integer.parseInt(request.getParameter("asistenciaID"));

		int horarioID = Integer.parseInt(request.getParameter("horario"));
		int estudianteID = Integer.parseInt(request.getParameter("estudiante"));

		String estado = request.getParameter("estado");
		String comentario = request.getParameter("comentario");
		String fechaParam = request.getParameter("fechaDeClase");
		Date fecha = null;

		

		if (fechaParam != null && !fechaParam.isEmpty()) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date utilDate = formato.parse(fechaParam); // Convertir String a java.util.Date
				fecha = new java.sql.Date(utilDate.getTime()); // Convertir java.util.Date a java.sql.Date
				System.out.println("Fecha convertida (java.sql.Date): " + fecha);

				AsistenciaEstudiante asistenciaEstudiante = modelAsistencia.obtenerPorID(asistenciaID);
				asistenciaEstudiante.setEstadoAsistencia(estado);
				asistenciaEstudiante.setComentario(comentario);
				asistenciaEstudiante.setFechaDeClase(fecha);

				// Registrar la fecha de actualización
				asistenciaEstudiante.setFechaActualizacion(new java.sql.Date(System.currentTimeMillis()));

				int resultado = modelAsistencia.actualizar(asistenciaEstudiante);
				String mensaje = "";

				if (resultado > 0) {
					mensaje = "Asistencia actualizada correctamente.";
					request.getSession().setAttribute("mensaje", mensaje); // ✅ Guardar en sesión

					response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID="
							+ horarioID + "&estudianteID=" + estudianteID);
				} else {
					mensaje = modelAsistencia.getMensajeError();
					request.getSession().setAttribute("mensaje", mensaje); // ✅ Guardar en sesión
					response.sendRedirect("AsistenciaEstudianteServlet?tipo=editar&id=" + asistenciaID);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				request.getSession().setAttribute("mensaje", "Formato de fecha inválido."); // ✅ Guardar en sesión
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=editar&id=" + asistenciaID);
			}
		}
	}
	
	
	

	private void registrarAsistencia(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int horarioID = Integer.parseInt(request.getParameter("horarioID"));
	    int estudianteID = Integer.parseInt(request.getParameter("estudianteID"));
	    String estado = request.getParameter("estado");
	    String comentario = request.getParameter("comentario");
	    String fechaString = request.getParameter("fecha");
	   

	    

	    
	    if (fechaString != null && !fechaString.isEmpty()) {
	        try {
	        	
	       
	        	 Date fecha = null;
	            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate = formato.parse(fechaString);
	            fecha = new java.sql.Date(utilDate.getTime());
	            
	            
	       	 // Mostrar en consola los parámetros obtenidos
		 		System.out.println("Parámetros obtenidos al registrar Asistencia:");
		 		System.out.println("ID Asistencia: " + horarioID);
		 		System.out.println("Estado: " + estado);
		 		System.out.println("Comentario: " + comentario);
		 		System.out.println("estudiante (String): " + estudianteID);
		 		System.out.println("Fecha (String): " + fechaString);
		 		 System.out.println("Fecha convertida (java.sql.Date): " + fecha);
		 		 System.out.println("Usuario: " + request.getSession().getAttribute("nombreUsuario"));
		 		 
		 		  AsistenciaEstudiante asistenciaEstudiante = new AsistenciaEstudiante();
		 		    asistenciaEstudiante.setHorarioID(horarioID);
		 		    asistenciaEstudiante.setEstudianteID(estudianteID);
		 		    asistenciaEstudiante.setEstadoAsistencia(estado);
		 		    asistenciaEstudiante.setComentario(comentario);
		 		    asistenciaEstudiante.setFechaDeClase(fecha);
		 		    asistenciaEstudiante.setUsuarioRegistro((String) request.getSession().getAttribute("nombreUsuario"));

		 		    int resultado = modelAsistencia.registrar(asistenciaEstudiante);
		 		    String mensaje = "";
		 		   if (resultado > 0) {
		 		        mensaje = "Asistencia registrada correctamente.";
		 		        request.getSession().setAttribute("mensaje", mensaje);
		 		        response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID="
		 		                + horarioID + "&estudianteID=" + estudianteID);
		 		    } else {
		 		        mensaje = modelAsistencia.getMensajeError();
		 		        request.getSession().setAttribute("mensaje", mensaje);
		 		        
		 		   

		 				request.setAttribute("horarioID", horarioID);
		 				request.setAttribute("EstudianteID", estudianteID);
		 				request.getRequestDispatcher("AsistenciaEstudianteServlet?tipo=nuevo").forward(request, response);
		 		    }
	           
	        } catch (ParseException e) {
	            e.printStackTrace();
	            request.getSession().setAttribute("mensaje", "Error");
	            response.sendRedirect("AsistenciaEstudianteServlet?tipo=nuevo");
	            return;  // ✅ Evita continuar con la ejecución si hay error en la fecha
	        }
	    }


	

	   
	}

	
	
	private void reporteAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HorarioModel modelHorario = new HorarioModel();
		// AsistenciaEstudianteModel modelAsistenciaEstudiante = new
		// AsistenciaEstudianteModel();

		// Obtener el horarioID seleccionado del JSP
		String horarioIDParam = request.getParameter("horarioID");

		List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<HorarioEstudiante>(); // Evita null

		if (horarioIDParam != null && !horarioIDParam.isEmpty()) {
			try {
				int horarioID = Integer.parseInt(horarioIDParam);
				listaHorarioEstudiante = modelAsistencia.obtenerEstudiantesPorHorario(horarioID);
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "Error: El ID del horario no es válido.");
				request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
				return; // Detiene la ejecución para evitar errores.
			}
		}

		// Obtener la lista de horarios (esto siempre debe cargarse)
		List<Horario> listaHorario = modelHorario.listHorario();

		String IDestudiante = request.getParameter("estudianteID");

		request.setAttribute("listaHorario", listaHorario);
		request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

		System.out.println("📌 Consultando asistencia del estudiante:");
		System.out.println("Horario ID: " + horarioIDParam);
		System.out.println("Estudiante ID: " + IDestudiante);

		request.setAttribute("IDestudiante", IDestudiante);
		request.setAttribute("IDhorario", horarioIDParam);

		request.getRequestDispatcher("reporte/reporteAsistencia.jsp").forward(request, response);
	}
	
	
	
	private void reportelistAsistenciaxAlumno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HorarioModel modelHorario = new HorarioModel();
		String horarioIDParam = request.getParameter("horarioID");
		String estudianteIDParam = request.getParameter("estudianteID");

		HttpSession session = request.getSession();

		// Recuperar de la sesión si no hay parámetros en la URL
		if ((horarioIDParam == null || horarioIDParam.isEmpty()) && session.getAttribute("horarioID") != null) {
			horarioIDParam = session.getAttribute("horarioID").toString();
		}
		if ((estudianteIDParam == null || estudianteIDParam.isEmpty())
				&& session.getAttribute("estudianteID") != null) {
			estudianteIDParam = session.getAttribute("estudianteID").toString();
		}

		if (horarioIDParam == null || horarioIDParam.isEmpty()) {
			System.out.println("Error: horarioID es nulo o vacío.");
			List<Horario> listaHorario = modelHorario.listHorario();
			request.setAttribute("listaHorario", listaHorario);
			request.setAttribute("error", "Debe seleccionar un horario.");
			request.getRequestDispatcher("reporte/reporteAsistencia.jsp").forward(request, response);
			return;
		}

		List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<>();
		List<Horario> listaHorario = modelHorario.listHorario();
		request.setAttribute("listaHorario", listaHorario);

		try {
			int horarioID = Integer.parseInt(horarioIDParam);
			listaHorarioEstudiante = modelAsistencia.obtenerEstudiantesPorHorario(horarioID);
			request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

			List<AsistenciaEstudiante> listaAsistenciaEstudiante = new ArrayList<>();
			if (estudianteIDParam != null && !estudianteIDParam.isEmpty()) {
				AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();
				listaAsistenciaEstudiante = modelAsistencia.obtenerAsistencia(horarioIDParam, estudianteIDParam);
				request.setAttribute("listaAsistenciaEstudiante", listaAsistenciaEstudiante);
			}

			// Guardar en la sesión para futuras consultas
			session.setAttribute("horarioID", horarioIDParam);
			session.setAttribute("estudianteID", estudianteIDParam);

		} catch (NumberFormatException e) {
			System.out.println("Error: No se pudo convertir horarioID a número entero.");
			request.setAttribute("error", "Formato de ID inválido.");
			request.getRequestDispatcher("reporte/reporteAsistencia.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("reporte/reporteAsistencia.jsp").forward(request, response);
	}
	
	
	
	
	  protected void generarReporteAsistencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		  String horarioIDParam = request.getParameter("IDdeHorario");
		  String estudianteIDParam = request.getParameter("IDdeEstudiante");


			ReporteAsistenciaModel.setHorarioID(horarioIDParam);
			ReporteAsistenciaModel.setEstudianteID(estudianteIDParam);
			
			
			// Ahora puedes obtenerlos en cualquier parte de tu código
			System.out.println("Horario ID: " + ReporteAsistenciaModel.getHorarioID());
			System.out.println("Estudiante ID: " + ReporteAsistenciaModel.getEstudianteID());
			
			
		  String filePath = "reporte_padres_tutores.pdf";
	        ReporteAsistenciaModel.generarReporte(filePath);

	        // Descargar el archivo PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
	        try (FileInputStream fis = new FileInputStream(filePath)) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = fis.read(buffer)) != -1) {
	                response.getOutputStream().write(buffer, 0, bytesRead);
	            }
	        }
	    }
	  
	  
	  
		private void generarAutomatico(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String horarioIDParam = request.getParameter("horarioID");
			String estudianteIDParam = request.getParameter("estudianteID");

			System.out.println("id" + horarioIDParam);
			System.out.println("id" + estudianteIDParam );
			
			HttpSession session = request.getSession();

			// Recuperar de la sesión si no hay parámetros en la URL
			if ((horarioIDParam == null || horarioIDParam.isEmpty()) && session.getAttribute("horarioID") != null) {
				horarioIDParam = session.getAttribute("horarioID").toString();
			}
			if ((estudianteIDParam == null || estudianteIDParam.isEmpty())
					&& session.getAttribute("estudianteID") != null) {
				estudianteIDParam = session.getAttribute("estudianteID").toString();
			}

	
			
			
			int horarioID = Integer.parseInt(horarioIDParam);
			int estudianteID = Integer.parseInt(estudianteIDParam);
			
			System.out.println("id" + horarioID);
			System.out.println("id" + estudianteID );
			
	
			int resultado = modelAsistencia.generarAutomatico(horarioID,estudianteID);


			if (resultado > 0) {
				session.setAttribute("mensaje", "Asistencia generadas correctamente.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=" + horarioID
						+ "&estudianteID=" + estudianteID);
			} else {
				session.setAttribute("error", "No se puede generar estas asistencias.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=nuevo");
			}
		}
		
		
		
		
		private void aprobar(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();
			HttpSession session = request.getSession();

			int asistenciaID = Integer.parseInt(request.getParameter("id"));
			int resultado = modelAsistenciaEstudiante.aprobar(asistenciaID);

			String horarioID = (session.getAttribute("horarioID") != null) ? session.getAttribute("horarioID").toString()
					: "";
			String estudianteID = (session.getAttribute("estudianteID") != null)
					? session.getAttribute("estudianteID").toString()
					: "";

			if (resultado > 0) {
				session.setAttribute("mensaje", "Asistencia Actualizada correctamente.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=" + horarioID
						+ "&estudianteID=" + estudianteID);
			} else {
				session.setAttribute("error", "No se puede actualizar esta asistencia.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=list");
			}
		}
		
		
		
		private void rechazar(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();
			HttpSession session = request.getSession();

			int asistenciaID = Integer.parseInt(request.getParameter("id"));
			int resultado = modelAsistenciaEstudiante.rechazar(asistenciaID);

			String horarioID = (session.getAttribute("horarioID") != null) ? session.getAttribute("horarioID").toString()
					: "";
			String estudianteID = (session.getAttribute("estudianteID") != null)
					? session.getAttribute("estudianteID").toString()
					: "";

			if (resultado > 0) {
				session.setAttribute("mensaje", "Asistencia actualizada correctamente.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=" + horarioID
						+ "&estudianteID=" + estudianteID);
			} else {
				session.setAttribute("error", "No se puede eliminar esta asistencia.");
				response.sendRedirect("AsistenciaEstudianteServlet?tipo=list");
			}
		}
	  

}
