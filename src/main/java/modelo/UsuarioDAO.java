package modelo;

import entidades.Usuario;
import interfaces.IUsuarioDAO;
import util.MySqlConexion;

import java.sql.*;

public class UsuarioDAO implements IUsuarioDAO {

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


