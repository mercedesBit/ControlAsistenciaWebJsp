package interfaces;

import java.util.List;

import entidades.Horario;

public interface HorarioInterface {

	public List<Horario> listHorario();
	public int registrarHorario(Horario horario);
	public Horario obtenerHorario(int id);
	public int editarHorario(Horario horario);
	public int eliminarHorario(int id);
}
