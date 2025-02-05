package interfaces;

import java.util.List;

import entidades.Profesor;
//METODOS
public interface ProfesorInterface {
	
	public List<Profesor> listProfesor();
	public int registrarProfesor(Profesor profesor);
	public Profesor obtenerProfesor(int id);
	public int editarProfesor(Profesor profesor);
	public int eliminarProfesor(int id);

}
