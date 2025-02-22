package servlets;

import entidades.Curso;
import modelo.CursoModel;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CursoModel cursoModel = new CursoModel();

	LocalDate fechaActual = LocalDate.now();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if (accion == null) {
			accion = "listar";
		}

		switch (accion) {
		case "nuevo":
			mostrarFormularioNuevo(request, response);
			break;
		case "editar":
			mostrarFormularioEdicion(request, response);
			break;
		case "eliminar":
			eliminarCurso(request, response);
			break;
		case "detalle":
			mostrarDetalle(request, response);
			break;
		default:
			listarCursos(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		switch (accion) {
		case "registrar":
			registrarCurso(request, response);
			break;
		case "actualizar":
			actualizarCurso(request, response);
			break;
		}
	}

	private void listarCursos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Curso> cursos = cursoModel.listarTodos();
		request.setAttribute("cursos", cursos);
		request.getRequestDispatcher("curso/listCurso.jsp").forward(request, response);
	}

	
	//REGISTRAR CURSO
	private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Llamamos al método que obtiene todos los ciclos
	    List<Curso> ciclos = cursoModel.obtenerTodosLosCiclos();
	    
	    // Agregamos la lista de ciclos al request para que esté disponible en el JSP
	    request.setAttribute("ciclos", ciclos);
		
		
		request.getRequestDispatcher("curso/regCurso.jsp").forward(request, response);
	}

	
	private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cursoID = Integer.parseInt(request.getParameter("id"));
		Curso curso = cursoModel.obtenerPorID(cursoID);
		
		
		
		// Llamamos al método que obtiene todos los ciclos
	    List<Curso> ciclos = cursoModel.obtenerTodosLosCiclos();
	    
	    // Agregamos la lista de ciclos al request para que esté disponible en el JSP
	    request.setAttribute("ciclos", ciclos);
		
		
		request.setAttribute("curso", curso);
		request.getRequestDispatcher("curso/editCurso.jsp").forward(request, response);
	}

	private void mostrarDetalle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cursoID = Integer.parseInt(request.getParameter("id"));
		
		Curso curso = cursoModel.obtenerPorID(cursoID);
	
		request.setAttribute("curso", curso);
		request.getRequestDispatcher("curso/detCurso.jsp").forward(request, response);
	}

	
	
	private void registrarCurso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codigoCurso = request.getParameter("codigoCurso");
		String nombreCurso = request.getParameter("nombreCurso");
		String descripcion = request.getParameter("descripcion");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		String ciclo = request.getParameter("ciclo");
		String nivel = request.getParameter("nivel");
		String notas = request.getParameter("notas");


		Curso curso = new Curso();
		curso.setCodigoCurso(codigoCurso);
		curso.setNombreCurso(nombreCurso);
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
	}

	private void actualizarCurso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cursoID = Integer.parseInt(request.getParameter("cursoID"));
		String codigoCurso = request.getParameter("codigoCurso");
		String nombreCurso = request.getParameter("nombreCurso");
		String descripcion = request.getParameter("descripcion");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		String ciclo = request.getParameter("ciclo");
		String nivel = request.getParameter("nivel");
		String estado = request.getParameter("estado");
		String notas = request.getParameter("notas");
		
		Curso curso = cursoModel.obtenerPorID(cursoID);
		curso.setCodigoCurso(codigoCurso);
		curso.setNombreCurso(nombreCurso);
		curso.setDescripcion(descripcion);
		curso.setCreditos(creditos);
		curso.setCiclo(ciclo);
		curso.setNivel(nivel);
		curso.setEstado(estado);

		curso.setNotas(notas);

		curso.setFechaActualizacion(Date.valueOf(fechaActual));
	

		int resultado = cursoModel.actualizar(curso);

		if (resultado > 0) {
			response.sendRedirect("CursoServlet?accion=listar");
		} else {
			request.setAttribute("error", "Error al actualizar el curso");
			request.getRequestDispatcher("curso/editCurso.jsp").forward(request, response);
		}
	}

	private void eliminarCurso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		int cursoID = Integer.parseInt(request.getParameter("id"));
		int resultado = cursoModel.eliminar(cursoID);

		if (resultado > 0) {
			response.sendRedirect("CursoServlet?accion=listar");
		} else {
			session.setAttribute("error", "No se puede eliminar este curso ya que está siendo usado");
			response.sendRedirect("CursoServlet?accion=listar");

			
		}
	}
}
