package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Rol;
import entidades.Usuario;
import modelo.MySqlUsuarioDAO;

/**
 * Servlet implementation class UsuariosServlet
 */
@WebServlet("/ServletUsuarios")
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

		public ServletUsuarios() {
			super();
		}
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("tipo");
		switch(tipo) {
        case "list" : listarUsuario(request, response); break;
        case "regist" : registUsuario(request, response); break;
        case "info": detalleUsuario(request, response); break;
        case "modif": modificarUsuario(request, response); break;
        case "edit": actualizarUsuario(request, response); break;
        case "delete": eliminarUsuario(request, response); break;        
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("usuario/regUsuario.jsp").forward(request, response);
        }
	}
	
	private void listarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySqlUsuarioDAO model=new MySqlUsuarioDAO();
		List<Usuario> lista=model.listUsuario();
				
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("usuario/listUsuario.jsp").forward(request, response);
	}

	private void registUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nombreUsuario= request.getParameter("txtNombreUsuario");
		String 	contraseña=request.getParameter("txtContrasena");
		int roleID=Integer.parseInt(request.getParameter("txtRoleID"));
		String estado=request.getParameter("txtEstado");
		
		Usuario usuario=new Usuario();
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setContrasena(contraseña);
		Rol rol=new Rol();
		usuario.setEstado(estado);
		rol.setRoleID(roleID);
		usuario.setRol(rol);
		
		MySqlUsuarioDAO model=new MySqlUsuarioDAO();
		int value = model.registrarUsuario(usuario);
        
        if(value == 1) {
            listarUsuario(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("usuario/regUsuario.jsp").forward(request, response);
        }
	}
	private void detalleUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("id"));
        
        MySqlUsuarioDAO model = new MySqlUsuarioDAO();
        
        Usuario usuario = model.obtenerUsuario(idUsuario);
        request.setAttribute("usuarioData", usuario);
        request.getRequestDispatcher("usuario/detUsuario.jsp").forward(request, response);
	}
	private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idUsuario = Integer.parseInt(request.getParameter("id"));
	       
	     MySqlUsuarioDAO model = new MySqlUsuarioDAO();
	     Usuario usuario = model.obtenerUsuario(idUsuario);
	        
	     request.setAttribute("usuarioData", usuario);
	     request.getRequestDispatcher("usuario/editUsuario.jsp").forward(request, response);
	}
	
	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MySqlUsuarioDAO model = new MySqlUsuarioDAO();
        int value = model.eliminarUsuario(id);
        
        if(value == 1) {
            listarUsuario(request, response);          
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("usuario/listUsuario.jsp").forward(request, response);         
        }
		
	}
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("txtidUsuario"));
        String nombreUsuario = request.getParameter("txtNombreUsuario");
		String contraseña=request.getParameter("txtContrasena");
		int roleID = Integer.parseInt(request.getParameter("txtRoleID"));
		String estado=request.getParameter("txtEstado");
		
		Usuario usuario = new Usuario();
        usuario.setUsuarioID(id);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contraseña);
        
        Rol rol = new Rol();
        rol.setRoleID(roleID);;
        usuario.setRol(rol);
        
        usuario.setEstado(estado);
        
        MySqlUsuarioDAO model=new MySqlUsuarioDAO();
        int value = model.editarUsuario(usuario);
        if(value == 1) {
            listarUsuario(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("usuario/listUsuario.jsp").forward(request, response);
        }        
	}

}
