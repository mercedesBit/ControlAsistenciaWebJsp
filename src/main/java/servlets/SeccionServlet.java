package servlets;

import entidades.Seccion;
import modelo.SeccionModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/SeccionServlet")
public class SeccionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SeccionModel seccionModel = new SeccionModel();

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
                eliminarSeccion(request, response);
                break;
            case "detalle":
                mostrarDetalle(request, response);
                break;
            default:
                listarSecciones(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "registrar":
                registrarSeccion(request, response);
                break;
            case "actualizar":
                actualizarSeccion(request, response);
                break;
        }
    }

    private void listarSecciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Seccion> secciones = seccionModel.listarTodos();
        request.setAttribute("secciones", secciones);
        request.getRequestDispatcher("seccion/listSeccion.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("seccion/regSeccion.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seccionID = Integer.parseInt(request.getParameter("id"));
        Seccion seccion = seccionModel.obtenerPorID(seccionID);
        request.setAttribute("seccion", seccion);
        request.getRequestDispatcher("seccion/editSeccion.jsp").forward(request, response);
    }

    private void mostrarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seccionID = Integer.parseInt(request.getParameter("id"));
        Seccion seccion = seccionModel.obtenerPorID(seccionID);
        request.setAttribute("seccion", seccion);
        request.getRequestDispatcher("seccion/detSeccion.jsp").forward(request, response);
    }

    private void registrarSeccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreSeccion = request.getParameter("nombreSeccion");
        String numeroAula = request.getParameter("numeroAula");
        String estado = request.getParameter("estado");

        Seccion seccion = new Seccion();
        seccion.setNombreSeccion(nombreSeccion);
        seccion.setNumeroAula(numeroAula);
        seccion.setEstado(estado);
        seccion.setFechaRegistro(new Date());
        seccion.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));

        int resultado = seccionModel.registrar(seccion);
        
        if (resultado > 0) {
            response.sendRedirect("SeccionServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al registrar la sección");
            request.getRequestDispatcher("seccion/editSeccion.jsp").forward(request, response);
        }
    }

    private void actualizarSeccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seccionID = Integer.parseInt(request.getParameter("seccionID"));
        String nombreSeccion = request.getParameter("nombreSeccion");
        String numeroAula = request.getParameter("numeroAula");
        String estado = request.getParameter("estado");

        Seccion seccion = seccionModel.obtenerPorID(seccionID);
        seccion.setNombreSeccion(nombreSeccion);
        seccion.setNumeroAula(numeroAula);
        seccion.setEstado(estado);
        seccion.setFechaActualizacion(new Date());

        int resultado = seccionModel.actualizar(seccion);
        
        if (resultado > 0) {
            response.sendRedirect("SeccionServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al actualizar la sección");
            request.getRequestDispatcher("seccion/editSeccion.jsp").forward(request, response);
        }
    }

    private void eliminarSeccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seccionID = Integer.parseInt(request.getParameter("id"));
        int resultado = seccionModel.eliminar(seccionID);
        
        if (resultado > 0) {
            response.sendRedirect("SeccionServlet?accion=listar");
        } else {
            request.setAttribute("error", "Error al eliminar la sección");
            request.getRequestDispatcher("listSeccion.jsp").forward(request, response);
        }
    }
}
