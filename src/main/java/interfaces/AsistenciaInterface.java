package interfaces;

import java.sql.Date;
import java.util.List;
import entidades.Asistencia;
import entidades.Curso;
//METODOS 

public interface AsistenciaInterface {
	
	public List<Asistencia> listAsistencia();
    public int registrarAsistencia(List<Asistencia> lstAsistencia);
    public List<Asistencia> obtenerAsistencia(Curso curso, Date fechaInicial, Date fechaFinal);
    public int editarAsistencia(List<Asistencia> lstAsistencia);
    public int eliminarAsistencia(List<Asistencia> lstAsistencia);
    public int eliminarAsistencia(int id);
    List<Asistencia> obtenerAsistenciaPorTipoAndFecha(String tipoAsistencia, Date fecha);
	
}
