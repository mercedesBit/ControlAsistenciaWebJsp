package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Rol;
import entidades.Usuario;
import interfaces.UsuarioDAO;
import util.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO{

	@Override
	public List<Usuario> listUsuario() {
		List<Usuario> listUsuarios= new ArrayList<Usuario>();
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "SELECT u.*, r.NombreRole\r\n"
	        		+ "FROM usuarios u\r\n"
	        		+ "INNER JOIN roles r ON u.RoleID = r.RoleID";
	        psm = cn.prepareStatement(sql);
	        rs = psm.executeQuery();
	        
	        while(rs.next()) {
	            Usuario reg = new Usuario();
	            reg.setUsuarioID(rs.getInt("UsuarioID"));
	            reg.setNombreUsuario(rs.getString("NombreUsuario"));
	            reg.setContrasena(rs.getString("Contrasena"));
	            Rol rol=new Rol();
	            rol.setNombreRole(rs.getString("NombreRole"));
	            reg.setRol(rol);
	            reg.setFechaRegistro(rs.getDate("FechaRegistro"));
	            reg.setEstado(rs.getString("Estado"));
	            listUsuarios.add(reg);
	        }            
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(rs != null) rs.close();
	            if(cn != null) cn.close();
	            if(psm != null) psm.close();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	            
	    return listUsuarios;
	}

	@Override
	public int registrarUsuario(Usuario user) {
		int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Insert into usuarios values (null, ?, ?, ?, ?, ?)";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, user.getNombreUsuario());
	        psm.setString(2, user.getContrasena());
	        psm.setInt(3, user.getRol().getRoleID());
	        psm.setDate(4, new java.sql.Date(System.currentTimeMillis()));
	        psm.setString(5, user.getEstado());
	        
	        value = psm.executeUpdate();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { 
	            if(cn != null) cn.close();
	            if(psm != null) psm.close();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }            
	    }            
	    return value;
	}

	@Override
	public Usuario obtenerUsuario(int usuarioID) {
		Usuario usua=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// Paso 1:
			cn=MySqlConexion.getConexion();
			// Paso 2:
			String sql="select *from usuarios where UsuarioID=?";
			// Paso 3:
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, usuarioID);
			rs = pstm.executeQuery();
			if(rs.next()) {
				usua = new Usuario();
				usua.setUsuarioID(rs.getInt("UsuarioID"));
				usua.setNombreUsuario(rs.getString("NombreUsuario"));
				usua.setContrasena(rs.getString("Contrasena"));
				
				Rol rol= new Rol();
		        rol.setRoleID(rs.getInt("RoleID"));
		            
		        usua.setRol(rol);
				usua.setFechaRegistro(rs.getDate("FechaRegistro"));
				usua.setEstado(rs.getString("Estado"));
			}
		} catch (Exception u) {
			u.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception u2) {
				u2.printStackTrace();
			}
		}
		return usua;
	}

	@Override
	public int editarUsuario(Usuario user) {
		int salida = 0;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="Update usuarios set NombreUsuario=?, Contrasena=?, RoleID=?, FechaRegistro=?, Estado=? where UsuarioID=?"; // ? --> Parámetros
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, user.getNombreUsuario());
			pstm.setString(2, user.getContrasena());
			pstm.setInt(3, user.getRol().getRoleID());
			pstm.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			pstm.setString(5, user.getEstado());
			pstm.setInt(6, user.getUsuarioID());

			// Paso 5: Ejecutar pstm y guardar su valor de retorno en la variable salida
			salida = pstm.executeUpdate();
		} catch (Exception u) {
			u.printStackTrace(); // Lanzar traza de error en la consola
		} finally {
			try {
				// Validar si los objetos pstm y cn estan creados
				if(pstm!= null) pstm.close();
				if(cn!= null) cn.close();
			} catch (Exception u2) {
				u2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int eliminarUsuario(int usuarioID) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from usuarios where UsuarioID=?"; 
			psm = cn.prepareStatement(sql);
			psm.setInt(1, usuarioID);
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(psm!= null) psm.close();
				if(cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		  Usuario usuario = null;
	        String query = "SELECT * FROM Usuarios WHERE NombreUsuario = ?";
	        
	        try (Connection conn = MySqlConexion.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, nombreUsuario);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                usuario = new Usuario();
	                usuario.setUsuarioID(rs.getInt("UsuarioID"));
	                usuario.setNombreUsuario(rs.getString("NombreUsuario"));
	                usuario.setContrasena(rs.getString("Contrasena"));
	                usuario.setRoleID(rs.getInt("RoleID"));
	                usuario.setEstado(rs.getString("Estado"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return usuario;
	}


}
