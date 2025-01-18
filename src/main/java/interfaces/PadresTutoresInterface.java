package interfaces;

import java.util.List;
import entidades.PadresTutores;

public interface PadresTutoresInterface {
    public List<PadresTutores> listPadresTutores();
    public int registrarPadresTutores(PadresTutores tutor);
    public PadresTutores obtenerPadresTutores(int id);
    public int editarPadresTutores(PadresTutores tutor);
    public int eliminarPadresTutores(int id);
}

