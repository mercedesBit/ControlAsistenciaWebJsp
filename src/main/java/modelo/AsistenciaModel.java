package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import entidades.Asistencia;
import entidades.Curso;
import entidades.Estudiante;
import entidades.PersonalAdministrativo;
import interfaces.AsistenciaInterface;
import util.MySqlConexion;

public class AsistenciaModel implements AsistenciaInterface{

	@Override
	public List<Asistencia> listAsistencia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registrarAsistencia(List<Asistencia> lstAsistencia) {
		  int value = 0;
		    Connection cn = null;
		    PreparedStatement psm = null;
		    
		    try {
		        cn = MySqlConexion.getConexion();
		        
		        for(Asistencia asistencia : lstAsistencia) {
		        	String sql = "Insert into asistencia values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			        psm = cn.prepareStatement(sql);
		        	psm.setInt(1, asistencia.getEstudiante().getEstudianteID());
		        	psm.setInt(2, asistencia.getCurso().getCursoID());
		        	psm.setInt(3, 1);
		        	psm.setTimestamp(4, asistencia.getHoraAsistencia());
		        	psm.setString(5, asistencia.getEstado());
		        	psm.setString(6, asistencia.getComentario());
		        	psm.setString(7, asistencia.getTipoAsistencia());
		        	psm.setDate(8, asistencia.getFechaRegistro());
		        	psm.setString(9, asistencia.getUsuarioRegistro());
		        	psm.setDate(10, null);
			        value = psm.executeUpdate();
		        }
		  
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try { 
		            if(cn != null) cn.close();
		            if(psm != null) psm.close();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }            
		    }            
		    return value;
	}

	@Override
	public List<Asistencia> obtenerAsistencia(Curso curso, Date fechaInicial, Date fechaFinal) {
		 List<Asistencia> listAsistencia = new ArrayList<Asistencia>();
		    Connection cn = null;
			CallableStatement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		        cn = MySqlConexion.getConexion();
		        String sql = "CALL usp_obtener_asistencia(?,?,?)";//PROCEDIMIENTO ALMACENADO
	  
		        stmt = (CallableStatement) cn.prepareCall(sql);
				stmt.setInt(1, curso.getCursoID());
				stmt.setDate(2, fechaInicial);
				stmt.setDate(3, fechaFinal);
				rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		            Asistencia reg = new Asistencia();
		            
		            reg.setAsistenciaID(rs.getInt("AsistenciaID"));
		            
		            Estudiante estudiante = new Estudiante();
		            estudiante.setEstudianteID(rs.getInt("EstudianteID"));
		            estudiante.setApellidos(rs.getString("Apellidos"));
		            estudiante.setNombres(rs.getString("Nombres"));
		            reg.setEstudiante(estudiante);
		            
		            Curso objCurso = new Curso();
		            objCurso.setCursoID(rs.getInt("CursoID"));
		            objCurso.setNombreCurso(rs.getString("NombreCurso"));

		            reg.setCurso(objCurso);
		            
		            PersonalAdministrativo personal = new PersonalAdministrativo();
		            personal.setPersonalID(rs.getInt("PersonalID"));
		            reg.setPersonal(personal);
		            
		            reg.setHoraAsistencia(rs.getTimestamp("HoraAsistencia"));	            
		            reg.setEstado(rs.getString("Estado"));		            
		            reg.setComentario(rs.getString("Comentario"));		            
		            reg.setTipoAsistencia(rs.getString("TipoAsistencia"));	            
		            reg.setFechaRegistro(rs.getDate("FechaRegistro"));		            
		            reg.setUsuarioRegistro(rs.getString("UsuarioRegistro"));	            
		            reg.setFechaActualizacion(rs.getDate("FechaActualizacion"));		            
		            listAsistencia.add(reg);
		        }
        
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if(rs != null) rs.close();
		            if(cn != null) cn.close();
		            if(stmt != null) stmt.close();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
		    }
		            
		    return listAsistencia;
	}

	@Override
	public List<Asistencia> obtenerAsistenciaPorTipoAndFecha(String tipoAsistencia, Date fecha) {
		 List<Asistencia> listAsistencia = new ArrayList<Asistencia>();
		    Connection cn = null;
			CallableStatement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		        cn = MySqlConexion.getConexion();
		        String sql = "CALL usp_obtener_asistencia_por_tipo_fecha(?,?)";
	  
		        stmt = (CallableStatement) cn.prepareCall(sql);
				stmt.setString(1, tipoAsistencia);
				stmt.setDate(2, fecha);
				rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		            Asistencia reg = new Asistencia();
		            
		            reg.setAsistenciaID(rs.getInt("AsistenciaID"));
		            
		            Estudiante estudiante = new Estudiante();
		            estudiante.setEstudianteID(rs.getInt("EstudianteID"));
		            estudiante.setApellidos(rs.getString("Apellidos"));
		            estudiante.setNombres(rs.getString("Nombres"));
		            reg.setEstudiante(estudiante);
		            
		            Curso objCurso = new Curso();
		            objCurso.setCursoID(rs.getInt("CursoID"));
		            objCurso.setNombreCurso(rs.getString("NombreCurso"));

		            reg.setCurso(objCurso);
		            
		            PersonalAdministrativo personal = new PersonalAdministrativo();
		            personal.setPersonalID(rs.getInt("PersonalID"));
		            reg.setPersonal(personal);
		            
		            reg.setHoraAsistencia(rs.getTimestamp("HoraAsistencia"));	            
		            reg.setEstado(rs.getString("Estado"));		            
		            reg.setComentario(rs.getString("Comentario"));		            
		            reg.setTipoAsistencia(rs.getString("TipoAsistencia"));	            
		            reg.setFechaRegistro(rs.getDate("FechaRegistro"));		            
		            reg.setUsuarioRegistro(rs.getString("UsuarioRegistro"));	            
		            reg.setFechaActualizacion(rs.getDate("FechaActualizacion"));		            
		            listAsistencia.add(reg);
		        }
        
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if(rs != null) rs.close();
		            if(cn != null) cn.close();
		            if(stmt != null) stmt.close();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
		    }
		            
		    return listAsistencia;
	}
	@Override
	public int editarAsistencia(List<Asistencia> lstAsistencia) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarAsistencia(List<Asistencia> lstAsistencia) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarAsistencia(int id) {
		  int value = 0;
		    Connection cn = null;
		    PreparedStatement psm = null;
		    
		    try {
		        cn = MySqlConexion.getConexion();
		        String sql = "Delete from Asistencia where AsistenciaID=?";
		        psm = cn.prepareStatement(sql);
		        psm.setInt(1, id);
		        
		        value = psm.executeUpdate();
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if(psm != null) psm.close();
		            if(cn != null) cn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }    
		    
		    return value;
	}

}
