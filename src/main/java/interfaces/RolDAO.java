package interfaces;
import java.util.List;

import entidades.Rol;

public interface RolDAO {
    public List<Rol> listRol();
    public int registrarRol(Rol rol);
    public Rol obtenerRol(int id);
    public int editarRol(Rol rol);
    public int eliminarRol(int id);
}
