package interfaces;

import entidades.PersonalAdministrativo;
import java.util.List;

public interface PersonalAdministrativoModel {
    List<PersonalAdministrativo> listarTodos();
    PersonalAdministrativo obtenerPorID(int id);
    int registrar(PersonalAdministrativo personal);
    int actualizar(PersonalAdministrativo personal);
    int eliminar(int id);
}