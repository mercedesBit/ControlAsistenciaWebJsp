package interfaces;

import entidades.Usuario;

public interface IUsuarioDAO {
    Usuario obtenerUsuarioPorNombre(String nombreUsuario);
}
