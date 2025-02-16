package interfaces;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Asistencia;
import entidades.AsistenciaEstudiante;
import entidades.Curso;
//METODOS 
import entidades.HorarioEstudiante;

import java.util.List;

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
