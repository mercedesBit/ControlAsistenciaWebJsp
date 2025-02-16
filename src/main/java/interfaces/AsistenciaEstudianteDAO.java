package interfaces;

import java.util.List;

import entidades.AsistenciaEstudiante;
//METODOS 
import entidades.HorarioEstudiante;

public interface AsistenciaEstudianteDAO {
	

    List<HorarioEstudiante> obtenerEstudiantesPorHorario(int horarioID);
    
    
    List<HorarioEstudiante> listHorario();
    

	List<AsistenciaEstudiante> obtenerAsistencia(String horarioIDParam, String estudianteIDParam);
	
	AsistenciaEstudiante obtenerPorID(int asistenciaID);


	int eliminar(int asistenciaID);


	int actualizar(AsistenciaEstudiante asistenciaEstudiante);


	int generarAutomatico(int horarioid, int estudianteid);


	int aprobar(int asistenciaID);


	int rechazar(int asistenciaID);



}
