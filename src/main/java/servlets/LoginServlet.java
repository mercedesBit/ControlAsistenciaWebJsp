package servlets;

import entidades.Usuario;
import modelo.UsuarioDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) { // Validación de la contraseña
            // Crear o invalidar sesión
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
    		request.getSession().setAttribute("usuarioID", usuario.getUsuarioID());
    		System.out.print("Usuario: "+usuario.getNombreUsuario());
    		request.getSession().setAttribute("nombreUsuario", usuario.getNombreUsuario());
            // Redirigir según el rol
            switch (usuario.getRoleID()) {
                case 1:  // Administrador
            		request.getSession().setAttribute("rol", "Administrador");
                    response.sendRedirect("index.jsp");
                    break;
                case 2:  // Estudiante
            		request.getSession().setAttribute("rol", "Estudiante");
                    response.sendRedirect("index.jsp");
                    break;
                case 3:  // Profesor
            		request.getSession().setAttribute("rol", "Profesor");
                    response.sendRedirect("index.jsp");
                    break;
                default:
                    response.sendRedirect("Login.jsp?error=true");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
