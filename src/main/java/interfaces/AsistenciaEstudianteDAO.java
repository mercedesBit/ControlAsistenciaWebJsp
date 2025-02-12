package interfaces;

import java.sql.Date;
import java.util.List;
import entidades.Asistencia;
import entidades.AsistenciaEstudiante;
import entidades.Curso;
//METODOS 
import entidades.HorarioEstudiante;

import java.util.List;

public interface AsistenciaEstudianteDAO {
	

    List<HorarioEstudiante> obtenerEstudiantesPorHorario(int horarioID);
    
    
    List<HorarioEstudiante> listHorario();
    

    List<AsistenciaEstudiante> obtenerAsistencia(int horarioID, int estudianteID);
}
