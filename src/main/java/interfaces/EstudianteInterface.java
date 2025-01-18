package interfaces;

import java.util.List;

import entidades.Estudiante;
//METODOS
public interface EstudianteInterface {

	public List<Estudiante> listEstudiante();
	public int registrarEstudiante(Estudiante estudiante);
	public Estudiante obtenerEstudiante(int id);
	public int editarEstudiante(Estudiante curso);
	public int eliminarEstudiante(int id);
}
