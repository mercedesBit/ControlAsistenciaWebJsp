package interfaces;

import entidades.Curso;
import java.util.List;

public interface CursoInterface {
    // Métodos CRUD
    int registrar(Curso curso);
    int actualizar(Curso curso);
    int eliminar(int cursoID);
    Curso obtenerPorID(int cursoID);
    List<Curso> listarTodos();
}
