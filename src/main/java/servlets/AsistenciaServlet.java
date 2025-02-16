package servlets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entidades.Asistencia;
import entidades.Curso;
import entidades.Estudiante;
import entidades.Profesor;
import modelo.AsistenciaModel;
import modelo.CursoModel;
import modelo.EstudianteModel;
import modelo.ProfesorModel;

/**
 * Servlet implementation class AsistenciaServlet
 */
@WebServlet("/AsistenciaServlet")
public class AsistenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AsistenciaModel model = new AsistenciaModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AsistenciaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");

		switch (tipo) {
		case "list":
			listAsistencia(request, response);
			break;
		case "registGet":
			registAsistenciaGet(request, response);
			break;
		case "regist":
			registAsistencia(request, response);
			break;
		case "delete":
			eliminarAsistencia(request, response);
			break;
		case "viewreporte":
			viewGenerarReporte(request, response);
			break;
		case "reporte":
			generarReporte(request, response);
			break;
		default:
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("asistencia/regAsistencia.jsp").forward(request, response);
		}
	}

	protected void viewGenerarReporte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("reporte/reporteAsistencia.jsp").forward(request, response);
	}

	protected void listAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AsistenciaModel model = new AsistenciaModel();
		Date fechaInicio=new java.sql.Date(System.currentTimeMillis());
		Date fechaFin=new java.sql.Date(System.currentTimeMillis());;
		int cursoID=0;
		try {
		    fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
		    fechaFin = Date.valueOf(request.getParameter("fechaFin"));
		    cursoID = Integer.parseInt(request.getParameter("cursoID"));
			
		}catch (Exception e) {
			System.out.print("");
		}
		
		CursoModel cursoModel = new CursoModel();
		List<Curso> cursos = cursoModel.listarTodos();
		Curso curso = new Curso();
		curso.setCursoID(cursoID);


		List<Asistencia> lista = model.obtenerAsistencia(curso, fechaInicio, fechaFin);

		request.setAttribute("fechaInicio", fechaInicio);
		request.setAttribute("fechaFin", fechaFin);
		request.setAttribute("cursoID", cursoID);

		request.setAttribute("listaCursos", cursos);
		request.setAttribute("lista", lista);
		
		request.getRequestDispatcher("asistencia/listAsistencia.jsp").forward(request, response);
	}

	protected void registAsistenciaGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CursoModel cursoModel = new CursoModel();
		List<Curso> cursos = cursoModel.listarTodos();

		EstudianteModel estudiante = new EstudianteModel();

		request.setAttribute("listaEstudiante", estudiante.listEstudiante());
		request.setAttribute("listaCursos", cursos);
		request.getRequestDispatcher("asistencia/regAsistencia.jsp").forward(request, response);

	}

	protected void registAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EstudianteModel estudiante = new EstudianteModel();
		List<Estudiante> listEstudiante = estudiante.listEstudiante();
		List<Asistencia> listaAsistencias = new ArrayList<>();
		String cursoID = request.getParameter("cursoID");
		String estado = "Activo";
		Timestamp timestampActual = new Timestamp(System.currentTimeMillis());

		for (Estudiante objEstudiante : listEstudiante) {
			String tipoAsistencia = request.getParameter("tipoAsistencia_" + objEstudiante.getEstudianteID());

			Asistencia asistencia = new Asistencia();
			asistencia.setTipoAsistencia(tipoAsistencia);
			asistencia.setComentario("");
			asistencia.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));
			asistencia.setHoraAsistencia(timestampActual);
			asistencia.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));
			asistencia.setEstado(estado);

			Estudiante objEst = new Estudiante();
			objEst.setEstudianteID(objEstudiante.getEstudianteID());
			asistencia.setEstudiante(objEst);

			Curso curso = new Curso();
			curso.setCursoID(Integer.parseInt(cursoID));
			asistencia.setCurso(curso);

			listaAsistencias.add(asistencia);
		}


		AsistenciaModel model = new AsistenciaModel();
		@SuppressWarnings("unused")
		int result = model.registrarAsistencia(listaAsistencias);
		CursoModel cursoModel = new CursoModel();
		List<Curso> cursos = cursoModel.listarTodos();


		request.setAttribute("listaCursos", cursos);

		request.getRequestDispatcher("asistencia/listAsistencia.jsp").forward(request, response);
	}

	protected void eliminarAsistencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		AsistenciaModel model = new AsistenciaModel();
		@SuppressWarnings("unused")
		int value = model.eliminarAsistencia(id);

		CursoModel cursoModel = new CursoModel();
		List<Curso> cursos = cursoModel.listarTodos();

		Date fechaInicio = new java.sql.Date(System.currentTimeMillis());
		Date fechaFin = new java.sql.Date(System.currentTimeMillis());

		Curso curso = new Curso();
		curso.setCursoID(1);

		List<Asistencia> lista = model.obtenerAsistencia(curso, fechaInicio, fechaFin);

		request.setAttribute("lista", lista);
		request.setAttribute("listaCursos", cursos);

		request.getRequestDispatcher("asistencia/listAsistencia.jsp").forward(request, response);
	}

	protected void generarReporte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String estado = request.getParameter("estado");
		Date fecha = Date.valueOf(request.getParameter("fechaReporte"));
		String filePath = "reporte_asistencia.pdf";

		List<Asistencia> listaAsistencia = model.obtenerAsistenciaPorTipoAndFecha(estado, fecha);
		request.setAttribute("listaAsistencia", listaAsistencia);

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			// Título del reporte
			Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
			Paragraph title = new Paragraph("Reporte de Asistencia", font);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph(" ")); // Espacio en blanco

			// Tabla de datos
			PdfPTable table = new PdfPTable(5); // Número de columnas
			table.setWidthPercentage(100);

			// Encabezados de la tabla
			addTableHeader(table);

			// Datos de la tabla
			addRows(table, listaAsistencia);

			document.add(table);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

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

	private void addTableHeader(PdfPTable table) {
		Stream.of("ID Estudiante", "Nombres", "Apellidos", "Curso", "Profesor", "Tipo", "Fecha")
				.forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private void addRows(PdfPTable table, List<Asistencia> listaAsistencia) {
		for (Asistencia asistencia : listaAsistencia) {
			ProfesorModel profModel = new ProfesorModel();
			Profesor prof = profModel.obtenerProfesor(asistencia.getCurso().getProfesorID());
			table.addCell(String.valueOf(asistencia.getAsistenciaID()));
			table.addCell(asistencia.getEstudiante().getNombres());
			table.addCell(asistencia.getEstudiante().getApellidos());
			table.addCell(asistencia.getCurso().getNombreCurso());
			table.addCell(prof.getNombres() + " " + prof.getApellidos());
			table.addCell(asistencia.getTipoAsistencia());
			table.addCell(asistencia.getFechaRegistro().toString());
		}
	}
}
