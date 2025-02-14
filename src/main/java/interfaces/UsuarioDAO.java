package interfaces;

import java.util.List;

import entidades.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> listUsuario();
	public int registrarUsuario(Usuario usuario);
    public int editarUsuario(Usuario usuario);
	public Usuario obtenerUsuario(int usuarioID);
    public int eliminarUsuario(int usuarioID);
    Usuario obtenerUsuarioPorNombre(String nombreUsuario);
}
