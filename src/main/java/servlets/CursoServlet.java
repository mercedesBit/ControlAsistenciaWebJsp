package servlets;

import entidades.Curso;
import modelo.CursoModel;

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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Curso> cursos = cursoModel.listarTodos();
        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("curso/listCurso.jsp").forward(request, response);
    }
    
    

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("curso/regCurso.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cursoID = Integer.parseInt(request.getParameter("id"));
        Curso curso = cursoModel.obtenerPorID(cursoID);
        request.setAttribute("curso", curso);
        request.getRequestDispatcher("curso/editCurso.jsp").forward(request, response);
    }

    private void mostrarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cursoID = Integer.parseInt(request.getParameter("id"));
        Curso curso = cursoModel.obtenerPorID(cursoID);
        request.setAttribute("curso", curso);
        request.getRequestDispatcher("curso/detCurso.jsp").forward(request, response);
    }

    private void registrarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoCurso = request.getParameter("codigoCurso");
        String nombreCurso = request.getParameter("nombreCurso");
        String descripcion = request.getParameter("descripcion");
        int duracion = Integer.parseInt(request.getParameter("duracion"));
        String grado = request.getParameter("grado");
        String nivel = request.getParameter("nivel");
       //   Date fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
       // Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));
        String requisitosPrevios = request.getParameter("requisitosPrevios");
        int cantidadMaximaEstudiantes = Integer.parseInt(request.getParameter("cantidadMaximaEstudiantes"));
        String modalidad = request.getParameter("modalidad");
       //   int seccionID = Integer.parseInt(request.getParameter("seccionID"));
        String temario = request.getParameter("temario");
       //   String horario = request.getParameter("horario");
        String notas = request.getParameter("notas");
       //     int profesorID = Integer.parseInt(request.getParameter("profesorID"));

        Curso curso = new Curso();
        curso.setCodigoCurso(codigoCurso);
        curso.setNombreCurso(nombreCurso);
        curso.setDescripcion(descripcion);
        curso.setDuracion(duracion);
        curso.setGrado(grado);
        curso.setNivel(nivel);
        //curso.setFechaInicio(fechaInicio);
       // curso.setFechaFin(fechaFin);
        curso.setEstado("Activo");
        curso.setRequisitosPrevios(requisitosPrevios);
        curso.setCantidadMaximaEstudiantes(cantidadMaximaEstudiantes);
        curso.setModalidad(modalidad);
       // curso.setSeccionID(seccionID);
       // curso.setTemario(temario);
       //curso.setHorario(horario);
        curso.setNotas(notas);
       //curso.setProfesorID(profesorID);
        curso.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));
        curso.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));

        int resultado = cursoModel.registrar(curso);
        
        if (resultado > 0) {
            response.sendRedirect("CursoServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al registrar el curso");
            request.getRequestDispatcher("curso/regCurso.jsp").forward(request, response);
        }
    }

    private void actualizarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cursoID = Integer.parseInt(request.getParameter("cursoID"));
        String codigoCurso = request.getParameter("codigoCurso");
        String nombreCurso = request.getParameter("nombreCurso");
        String descripcion = request.getParameter("descripcion");
        int duracion = Integer.parseInt(request.getParameter("duracion"));
        String grado = request.getParameter("grado");
        String nivel = request.getParameter("nivel");
     //   Date fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
      //  Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));
        String estado = request.getParameter("estado");
        String requisitosPrevios = request.getParameter("requisitosPrevios");
        int cantidadMaximaEstudiantes = Integer.parseInt(request.getParameter("cantidadMaximaEstudiantes"));
        String modalidad = request.getParameter("modalidad");
     //   int seccionID = Integer.parseInt(request.getParameter("seccionID"));
     //   String temario = request.getParameter("temario");
     //   String horario = request.getParameter("horario");
        String notas = request.getParameter("notas");
     //   int profesorID = Integer.parseInt(request.getParameter("profesorID"));

        Curso curso = cursoModel.obtenerPorID(cursoID);
        curso.setCodigoCurso(codigoCurso);
        curso.setNombreCurso(nombreCurso);
        curso.setDescripcion(descripcion);
        curso.setDuracion(duracion);
        curso.setGrado(grado);
        curso.setNivel(nivel);
      //  curso.setFechaInicio(fechaInicio);
      //  curso.setFechaFin(fechaFin);
        curso.setEstado(estado);
        curso.setRequisitosPrevios(requisitosPrevios);
        curso.setCantidadMaximaEstudiantes(cantidadMaximaEstudiantes);
        curso.setModalidad(modalidad);
      //  curso.setSeccionID(seccionID);
      //  curso.setTemario(temario);
       // curso.setHorario(horario);
        curso.setNotas(notas);
     //   curso.setProfesorID(profesorID);
        curso.setFechaActualizacion(Date.valueOf(fechaActual));

        int resultado = cursoModel.actualizar(curso);
        
        if (resultado > 0) {
            response.sendRedirect("CursoServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al actualizar el curso");
            request.getRequestDispatcher("curso/editCurso.jsp").forward(request, response);
        }
    }

    private void eliminarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cursoID = Integer.parseInt(request.getParameter("id"));
        int resultado = cursoModel.eliminar(cursoID);
        
        if (resultado > 0) {
            response.sendRedirect("CursoServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al eliminar el curso");
            request.getRequestDispatcher("curso/listCurso.jsp").forward(request, response);
        }
    }
}

