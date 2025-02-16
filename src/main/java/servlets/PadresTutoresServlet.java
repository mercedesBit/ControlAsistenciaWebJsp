package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.PadresTutores;
import modelo.PadresTutoresModel;
import modelo.ReportePadresTutores;

/**
 * Servlet implementation class PadresTutoresServlet
 */
@WebServlet("/PadresTutoresServlet")
public class PadresTutoresServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PadresTutoresServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        switch(tipo) {
            case "list" : listPadresTutores(request, response); break;
            case "regist" : registPadresTutores(request, response); break;
            case "info": detallePadresTutores(request, response); break;
            case "modif": modificarPadresTutores(request, response); break;
            case "edit": actualizarPadresTutores(request, response); break;
            case "delete": eliminarPadresTutores(request, response); break; 
            case "viewreporte": viewGenerarReporte(request, response); break;
            case "reporte": generarReporte(request, response); break;

            default:
                request.setAttribute("mensaje", "Ocurrio un problema");
                request.getRequestDispatcher("padresTutores/regPadresTutores.jsp").forward(request, response);
        }    
    }
    protected void viewGenerarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PadresTutoresModel model = new PadresTutoresModel();
        List<PadresTutores> lista = model.listPadresTutores();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("reporte/reportePadresTutores.jsp").forward(request, response);
    }

    protected void listPadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PadresTutoresModel model = new PadresTutoresModel();
        List<PadresTutores> lista = model.listPadresTutores();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("padresTutores/listPadresTutores.jsp").forward(request, response);
    }

    protected void registPadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String correoElectronico = request.getParameter("txtCorreoElectronico");
        String telefonoMovil = request.getParameter("txtTelefonoMovil");
        String telefonoCasa = request.getParameter("txtTelefonoCasa");
        String direccion = request.getParameter("txtDireccion");
        String relacionEstudiante = request.getParameter("txtRelacionEstudiante");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String ocupacion = request.getParameter("txtOcupacion");
        String estado = request.getParameter("txtEstado");
        String redesSociales = request.getParameter("txtRedesSociales");

        PadresTutores tutor = new PadresTutores();
        tutor.setTipoDocumento(tipoDocumento);
        tutor.setNumeroDocumento(numeroDocumento);
        tutor.setNombres(nombres);
        tutor.setApellidos(apellidos);
        tutor.setCorreoElectronico(correoElectronico);
        tutor.setTelefonoMovil(telefonoMovil);
        tutor.setTelefonoCasa(telefonoCasa);
        tutor.setDireccion(direccion);
        tutor.setRelacionEstudiante(relacionEstudiante);
        tutor.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        tutor.setOcupacion(ocupacion);
        tutor.setEstado(estado);
        tutor.setRedesSociales(redesSociales);
        tutor.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));

        PadresTutoresModel model = new PadresTutoresModel();
        int value = model.registrarPadresTutores(tutor);

        if(value == 1) {
            listPadresTutores(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("padresTutores/regPadresTutores.jsp").forward(request, response);
        }
    }

    protected void detallePadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTutor = Integer.parseInt(request.getParameter("id"));
        PadresTutoresModel model = new PadresTutoresModel();
        PadresTutores tutor = model.obtenerPadresTutores(idTutor);
        request.setAttribute("tutorData", tutor);
        request.getRequestDispatcher("padresTutores/detPadresTutores.jsp").forward(request, response);
    }

    protected void modificarPadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTutor = Integer.parseInt(request.getParameter("id"));
        PadresTutoresModel model = new PadresTutoresModel();
        PadresTutores tutor = model.obtenerPadresTutores(idTutor);
        request.setAttribute("tutorData", tutor);
        request.getRequestDispatcher("padresTutores/editPadresTutores.jsp").forward(request, response);        
    }

    protected void actualizarPadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idTutor"));
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String correoElectronico = request.getParameter("txtCorreoElectronico");
        String telefonoMovil = request.getParameter("txtTelefonoMovil");
        String telefonoCasa = request.getParameter("txtTelefonoCasa");
        String direccion = request.getParameter("txtDireccion");
        String relacionEstudiante = request.getParameter("txtRelacionEstudiante");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String ocupacion = request.getParameter("txtOcupacion");
        String estado = request.getParameter("txtEstado");
        String redesSociales = request.getParameter("txtRedesSociales");

        PadresTutores tutor = new PadresTutores();
        tutor.setTutorID(id);
        tutor.setTipoDocumento(tipoDocumento);
        tutor.setNumeroDocumento(numeroDocumento);
        tutor.setNombres(nombres);
        tutor.setApellidos(apellidos);
        tutor.setCorreoElectronico(correoElectronico);
        tutor.setTelefonoMovil(telefonoMovil);
        tutor.setTelefonoCasa(telefonoCasa);
        tutor.setDireccion(direccion);
        tutor.setRelacionEstudiante(relacionEstudiante);
        tutor.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        tutor.setOcupacion(ocupacion);
        tutor.setEstado(estado);
        tutor.setRedesSociales(redesSociales);

        PadresTutoresModel model = new PadresTutoresModel();
        int value = model.editarPadresTutores(tutor);

        if(value == 1) {
            listPadresTutores(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("padresTutores/listPadresTutores.jsp").forward(request, response);
        }        
    }

    protected void eliminarPadresTutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PadresTutoresModel model = new PadresTutoresModel();
        int value = model.eliminarPadresTutores(id);

        if(value == 1) {
            listPadresTutores(request, response);             
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("padresTutores/listPadresTutores.jsp").forward(request, response);         
        }
    }
    protected void generarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = "reporte_asistencias_Alumno.pdf";
        ReportePadresTutores.generarReporte(filePath);

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
}
