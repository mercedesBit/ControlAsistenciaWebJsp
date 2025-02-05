package servlets;

import modelo.PersonalAdministrativoModel;
import entidades.PersonalAdministrativo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/PersonalAdministrativoServlet")
public class PersonalAdministrativoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonalAdministrativoModel personalModel;

    @Override
    public void init() throws ServletException {
        personalModel = new PersonalAdministrativoModel(); // Inicializa el modelo
    }

    // Método para manejar solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {
            listarPersonales(request, response); // Listar registros
        } else if (accion.equals("editar")) {
            editarPersonal(request, response); // Editar un registro
        } else if (accion.equals("eliminar")) {
            eliminarPersonal(request, response); // Eliminar un registro
        } else if (accion.equals("nuevo")) {
            mostrarFormularioCrear(request, response); // Mostrar formulario de creación
        }
    }

    // Método para manejar solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("guardar")) {
            registrarPersonal(request, response); // Registrar un nuevo registro
        } else if (accion.equals("editar")) {
            guardarEdicion(request, response); // Guardar edición de un registro
        }
    }

    // Método para listar todos los registros
    private void listarPersonales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("personales", personalModel.listarTodos());
        request.getRequestDispatcher("personal/listarPersonal.jsp").forward(request, response);
    }

    // Método para mostrar el formulario de creación
    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("personal/nuevoPersonal.jsp").forward(request, response);
    }

    // Método para editar un registro
    private void editarPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalID = Integer.parseInt(request.getParameter("id"));
        PersonalAdministrativo personal = personalModel.obtenerPorID(personalID);
        request.setAttribute("personal", personal);
        request.getRequestDispatcher("personal/editarPersonal.jsp").forward(request, response);
    }

    // Método para eliminar un registro
    private void eliminarPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalID = Integer.parseInt(request.getParameter("id"));
        personalModel.eliminar(personalID);
        response.sendRedirect("PersonalAdministrativoServlet?accion=listar");
    }

    private void registrarPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PersonalAdministrativo personal = new PersonalAdministrativo();

            // Capturar el personalID manualmente
            String personalIDParam = request.getParameter("personalID");
            if (personalIDParam != null && !personalIDParam.isEmpty()) {
                personal.setPersonalID(Integer.parseInt(personalIDParam));
            }

            // Verificar si el ID ya existe en la base de datos (solo para registrar, no para editar)
            if (personal.getPersonalID() > 0 && personalModel.obtenerPorID(personal.getPersonalID()) != null) {
                request.setAttribute("error", "El ID ingresado ya está registrado. Por favor, use un ID diferente.");
                request.getRequestDispatcher("personal/nuevoPersonal.jsp").forward(request, response);
                return;
            }

            // Asignar los valores del formulario
            personal.setTipoDocumento(request.getParameter("tipoDocumento"));
            personal.setNumeroDocumento(request.getParameter("numeroDocumento"));
            personal.setNombres(request.getParameter("nombres"));
            personal.setApellidos(request.getParameter("apellidos"));
            personal.setCorreoElectronico(request.getParameter("correoElectronico"));
            personal.setTelefonoMovil(request.getParameter("telefonoMovil"));
            personal.setTelefonoTrabajo(request.getParameter("telefonoTrabajo"));
            personal.setFechaNacimiento(java.sql.Date.valueOf(request.getParameter("fechaNacimiento")));
            personal.setDireccion(request.getParameter("direccion"));
            personal.setCargo(request.getParameter("cargo"));
            personal.setFechaContratacion(java.sql.Date.valueOf(request.getParameter("fechaContratacion")));
            personal.setEstado(request.getParameter("estado"));
            personal.setSueldo(new BigDecimal(request.getParameter("sueldo")));
            personal.setRedesSociales(request.getParameter("redesSociales"));
            personal.setFechaRegistro(java.sql.Date.valueOf(request.getParameter("fechaRegistro")));
            personal.setUsuarioRegistro((String)request.getSession().getAttribute("nombreUsuario"));
            personal.setFechaActualizacion(java.sql.Date.valueOf(request.getParameter("fechaActualizacion")));

            // Registrar el nuevo personal
            personalModel.registrar(personal);

            // Redirigir al listado después de guardar
            response.sendRedirect("PersonalAdministrativoServlet?accion=listar");

        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al registrar el personal: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }



    private void guardarEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int personalID = Integer.parseInt(request.getParameter("personalID"));
            PersonalAdministrativo personal = personalModel.obtenerPorID(personalID);

            // Si el personal existe, actualizar los datos
            if (personal != null) {
                personal.setTipoDocumento(request.getParameter("tipoDocumento"));
                personal.setNumeroDocumento(request.getParameter("numeroDocumento"));
                personal.setNombres(request.getParameter("nombres"));
                personal.setApellidos(request.getParameter("apellidos"));
                personal.setCorreoElectronico(request.getParameter("correoElectronico"));
                personal.setTelefonoMovil(request.getParameter("telefonoMovil"));
                personal.setTelefonoTrabajo(request.getParameter("telefonoTrabajo"));
                personal.setFechaNacimiento(java.sql.Date.valueOf(request.getParameter("fechaNacimiento")));
                personal.setDireccion(request.getParameter("direccion"));
                personal.setCargo(request.getParameter("cargo"));
                personal.setFechaContratacion(java.sql.Date.valueOf(request.getParameter("fechaContratacion")));
                personal.setEstado(request.getParameter("estado"));
                personal.setSueldo(new BigDecimal(request.getParameter("sueldo")));
                personal.setRedesSociales(request.getParameter("redesSociales"));
                personal.setFechaActualizacion(java.sql.Date.valueOf(request.getParameter("fechaActualizacion")));

                // Guardar los cambios en la base de datos
                personalModel.actualizar(personal);

                // Redirigir al listado después de guardar la edición
                response.sendRedirect("PersonalAdministrativoServlet?accion=listar");

            } else {
                request.setAttribute("error", "El personal con ID " + personalID + " no se encuentra en la base de datos.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al editar el personal: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
