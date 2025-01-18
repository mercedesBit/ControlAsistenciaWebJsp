package interfaces;

import entidades.Seccion;
import java.util.List;

public interface SeccionInterface {
    // Métodos CRUD
    int registrar(Seccion seccion);
    int actualizar(Seccion seccion);
    int eliminar(int seccionID);
    Seccion obtenerPorID(int seccionID);
    List<Seccion> listarTodos();
}