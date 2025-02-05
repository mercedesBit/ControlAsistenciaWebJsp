package interfaces;


import java.util.List;

import entidades.Matricula;

public interface MatriculaInterface {
	
	public int registrarMatricula(Matricula matricula);

	public List<Matricula> listMatricula();
}
