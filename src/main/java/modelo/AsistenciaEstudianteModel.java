package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entidades.AsistenciaEstudiante;
import entidades.HorarioEstudiante;
import interfaces.AsistenciaEstudianteDAO;
import util.MySqlConexion;

public class AsistenciaEstudianteModel implements AsistenciaEstudianteDAO {



	
//LISTA DE ESTUDIANTE DEPENDIENDO EL HORARIO 
	@Override
	public List<HorarioEstudiante> obtenerEstudiantesPorHorario(int horarioID) {
	    List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<>();
	    String query = "SELECT EstudianteID, Nombres, Apellidos FROM horario_estudiante WHERE HorarioID = ?";

	    try (Connection conn = MySqlConexion.getConexion();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, horarioID);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                HorarioEstudiante horarioEstudiante = new HorarioEstudiante();
	                horarioEstudiante.setEstudianteID(rs.getInt("EstudianteID"));  // Asigna el ID
	                horarioEstudiante.setNombre(rs.getString("Nombres"));        // Asigna el Nombre
	                horarioEstudiante.setApellido(rs.getString("Apellidos"));    // Asigna el Apellido
	                
	                listaHorarioEstudiante.add(horarioEstudiante);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaHorarioEstudiante;
	}

	
	
	
	
	//LISTA DE HORARIO DE ESTUDIANTE 
	     @Override
		public List<HorarioEstudiante> listHorario() {
		    List<HorarioEstudiante> listHorario = new ArrayList<HorarioEstudiante>();
		    Connection cn = null;
		    PreparedStatement psm = null;
		    ResultSet rs = null;
		    
		    try {
		        cn = MySqlConexion.getConexion();
		        String sql = "Select * from horario_estudiante";
		        psm = cn.prepareStatement(sql);
		        rs = psm.executeQuery();
		        
		        while(rs.next()) {
		        	HorarioEstudiante reg = new HorarioEstudiante();
		            reg.setEstudianteID(rs.getInt("EstudianteID"));
		            reg.setEstudianteID(rs.getInt("HorarioID"));
		          
		            listHorario.add(reg);
		        }            
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if(rs != null) rs.close();
		            if(cn != null) cn.close();
		            if(psm != null) psm.close();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
		    }
		            
		    return listHorario;
		}

	

    
    
    
	@Override
	  public List<AsistenciaEstudiante> obtenerAsistencia(int estudianteID, int horarioID) {
        List<AsistenciaEstudiante> listaAsistencia = new ArrayList<>();
        String sql = "SELECT * FROM asistencia_estudiante WHERE EstudianteID = ? AND HorarioID = ?";

     
        
        try (Connection con = MySqlConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteID);
            ps.setInt(2, horarioID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AsistenciaEstudiante asistencia = new AsistenciaEstudiante();
                asistencia.setAsistenciaID(rs.getInt("AsistenciaID"));
                asistencia.setEstudianteID(rs.getInt("EstudianteID"));
                asistencia.setHorarioID(rs.getInt("HorarioID"));
                asistencia.setEstadoAsistencia(rs.getString("EstadoAsistencia"));
                asistencia.setComentario(rs.getString("Comentario"));
                asistencia.setFechaDeClase(rs.getDate("FECHADECLASE"));
                asistencia.setDiaAsistencia(rs.getString("DIAASISTENCIA"));
                asistencia.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                asistencia.setFechaActualizacion(rs.getDate("FECHAACTUALIZACION"));

                listaAsistencia.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAsistencia;
    }








}
