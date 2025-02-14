package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.AsistenciaEstudiante;
import entidades.Curso;
import entidades.Estudiante;
import entidades.Horario;
import entidades.HorarioEstudiante;
import modelo.AsistenciaEstudianteModel;
import modelo.AsistenciaModel;
import modelo.EstudianteModel;
import modelo.HorarioModel;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.time.format.DateTimeParseException;


@WebServlet("/AsistenciaEstudianteServlet")
public class AsistenciaEstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AsistenciaEstudianteModel modelo = new AsistenciaEstudianteModel();

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
				
			case "actualizarlo":
				actualizarAsistencia(request, response);
				break;
			
	       
	        default:
	            request.setAttribute("mensaje", "Ocurrió un problema: tipo no válido.");
	            request.getRequestDispatcher("asistencia/asistencia.jsp").forward(request, response);
	    }
	    
	}
/*
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		switch (accion) {
		/*
		case "registrar":
			registrarCurso(request, response);
			break;
		case "actualizar":
			actualizarAsistencia(request, response);
			break;
		}
	} */
	
	public AsistenciaEstudianteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init() throws ServletException {
		modelo = new AsistenciaEstudianteModel();
	}

	
	AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();
	LocalDate fechaActual = LocalDate.now();

	private void listAsistencia(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HorarioModel modelHorario = new HorarioModel();
	  //  AsistenciaEstudianteModel modelAsistenciaEstudiante = new AsistenciaEstudianteModel();

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

	    request.setAttribute("listaHorario", listaHorario);
	    request.setAttribute("listaHorarioEstudiante", listaHorarioEstudiante);

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
	    if ((estudianteIDParam == null || estudianteIDParam.isEmpty()) && session.getAttribute("estudianteID") != null) {
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
		
	
		
		
		request.getRequestDispatcher("asistencia/regAsistencia.jsp").forward(request, response);
	}



	
	  protected void mostrarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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

		    String horarioID = (session.getAttribute("horarioID") != null) ? session.getAttribute("horarioID").toString() : "";
		    String estudianteID = (session.getAttribute("estudianteID") != null) ? session.getAttribute("estudianteID").toString() : "";

		    if (resultado > 0) {
		        session.setAttribute("mensaje", "Asistencia eliminada correctamente.");
		        response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=" + horarioID + "&estudianteID=" + estudianteID);
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

	  
	  /*
	  private void registrarAsistencia(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String estado = request.getParameter("estado");
			String Comentario = request.getParameter("Comentario");
			
			String descripcion = request.getParameter("descripcion");
			int creditos = Integer.parseInt(request.getParameter("creditos"));
			String ciclo = request.getParameter("ciclo");
			String nivel = request.getParameter("nivel");
			String notas = request.getParameter("notas");


			Curso curso = new Curso();
			curso.setCodigoCurso(estado);
			curso.setNombreCurso(Comentario);
			curso.setDescripcion(descripcion);
			curso.setCreditos(creditos);
			curso.setCiclo(ciclo);
			curso.setNivel(nivel);
			curso.setEstado("Activo");
			curso.setNotas(notas);
			curso.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));
			curso.setUsuarioRegistro((String) request.getSession().getAttribute("nombreUsuario"));
			
		
			


			int resultado = cursoModel.registrar(curso);

			if (resultado > 0) {
				response.sendRedirect("CursoServlet?accion=listar");
			} else {
				request.setAttribute("error", "Error al registrar el curso");
				request.getRequestDispatcher("curso/regCurso.jsp").forward(request, response);
			}
		}*/

	  
	  private void actualizarAsistencia(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		  
		  AsistenciaEstudianteModel modelAsistencia = new AsistenciaEstudianteModel();

		    int asistenciaID = Integer.parseInt(request.getParameter("asistenciaID"));
		    String estado = request.getParameter("estado");
		    String comentario = request.getParameter("comentario");
		    String fechaParam = request.getParameter("fechaDeClase");
		    Date fecha = null;

		    // Mostrar en consola los parámetros obtenidos
		    System.out.println("Parámetros obtenidos:");
		    System.out.println("ID Asistencia: " + asistenciaID);
		    System.out.println("Estado: " + estado);
		    System.out.println("Comentario: " + comentario);
		    System.out.println("Fecha (String): " + fechaParam);
	

		    if (fechaParam != null && !fechaParam.isEmpty()) {
		        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		        try {
		            java.util.Date utilDate = formato.parse(fechaParam); // Convertir String a java.util.Date
		            fecha = new java.sql.Date(utilDate.getTime()); // Convertir java.util.Date a java.sql.Date
		            System.out.println("Fecha convertida (java.sql.Date): " + fecha);
		            
		            
		    	    AsistenciaEstudiante asistenciaEstudiante = modelAsistencia.obtenerPorID(asistenciaID);
				    asistenciaEstudiante.setEstadoAsistencia(estado);
				    asistenciaEstudiante.setComentario(comentario); // Aquí tenías asistenciaEstudiante.setComentario(estado), corregido a comentario.
				    asistenciaEstudiante.setFechaDeClase(fecha);

				    // Registrar la fecha de actualización
				    asistenciaEstudiante.setFechaActualizacion(new java.sql.Date(System.currentTimeMillis()));

				    int resultado = modelAsistencia.actualizar(asistenciaEstudiante);
				    if (resultado > 0) {
					       
				        System.out.println("Parámetros obtenidos:");
				        response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno");
				    } else {
				       
				        System.out.println("Parámetros obtenidos: NINGUNO");
				        response.sendRedirect("AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno");
				    }
				    
				    
				    
		        } catch (ParseException e) {
		            e.printStackTrace();
		            request.setAttribute("error", "Formato de fecha inválido.");
		        }
		    }

	

		}

	  
	  }

