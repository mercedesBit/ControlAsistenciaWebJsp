package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Curso;
import entidades.Horario;
import entidades.Profesor;
import entidades.Seccion;
import interfaces.HorarioInterface;
import util.MySqlConexion;

public class HorarioModel implements HorarioInterface{

	@Override
	public List<Horario> listHorario() {
		List<Horario> listHorario = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "SELECT h.HorarioID, c.NombreCurso, p.Nombres AS NombreProfesor, p.Apellidos AS ApellidoProfesor, s.NombreSeccion, h.DiaSemana, h.HoraInicioFin, h.Modalidad, h.Estado \r\n"
	        		+ "FROM Horario h \r\n"
	        		+ "JOIN Curso c ON h.CursoID = c.CursoID \r\n"
	        		+ "JOIN Profesor p ON h.ProfesorID = p.ProfesorID \r\n"
	        		+ "JOIN Seccion s ON h.SeccionID = s.SeccionID";
	        psm = cn.prepareStatement(sql);
	        rs = psm.executeQuery();
	        
	        while(rs.next()) {
	        	Horario reg = new Horario();
                reg.setHorarioID(rs.getInt("HorarioID"));
                reg.setNombreCurso(rs.getString("NombreCurso"));
                reg.setNombreProfesor(rs.getString("NombreProfesor"));
                reg.setApellidoProfesor(rs.getString("ApellidoProfesor"));
                reg.setNombreSeccion(rs.getString("NombreSeccion"));
                reg.setDiaSemana(rs.getString("DiaSemana"));
                reg.setHoraInicioFin(rs.getString("HoraInicioFin"));
                reg.setModalidad(rs.getString("Modalidad"));
                reg.setEstado(rs.getString("Estado"));
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
	public int registrarHorario(Horario horario) {
		int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "INSERT INTO Horario values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        psm = cn.prepareStatement(sql);
	        
	        psm.setInt(1, horario.getCursoID());
	        psm.setInt(2, horario.getProfesorID());
	        psm.setInt(3, horario.getSeccionID());
	        psm.setString(4, horario.getDiaSemana());
	        psm.setString(5, horario.getHoraInicioFin());
	        psm.setDate(6, new java.sql.Date(horario.getFechaInicio().getTime()));
	        psm.setDate(7, new java.sql.Date(horario.getFechaFin().getTime()));
	        psm.setInt(8, horario.getMaxEstudiantes());
	        psm.setString(9, horario.getModalidad()); // ENUM (Presencial, Virtual, Semipresencial)
	        psm.setString(10, horario.getEstado());   // ENUM (Activo, Inactivo)
	        psm.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis())); // FechaRegistro
	        psm.setString(12, horario.getUsuarioRegistro());
	        psm.setTimestamp(13, new java.sql.Timestamp(System.currentTimeMillis())); // FechaActualizacion

	        value = psm.executeUpdate();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { 
	            if(psm != null) psm.close();
	            if(cn != null) cn.close();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }            
	    }            
	    return value;
	}

	@Override
	public Horario obtenerHorario(int id) {
		Horario horario = null;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;

	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "select * from horario where HorarioID=?";    
	        psm = cn.prepareStatement(sql);
	        psm.setInt(1, id);
	        rs = psm.executeQuery();

	        if (rs.next()) {
	            horario = new Horario();
	            horario.setHorarioID(rs.getInt("HorarioID"));
	            
	            Curso curso= new Curso();
	            curso.setCursoID(rs.getInt("CursoID"));
	            horario.setCurso(curso);
	            Profesor profesor= new Profesor();
	            profesor.setProfesorID(rs.getInt("ProfesorID"));
	            horario.setProfesor(profesor);
	            Seccion seccion= new Seccion();
	            seccion.setSeccionID(rs.getInt("SeccionID"));
	            horario.setSeccion(seccion);
	            
	            horario.setDiaSemana(rs.getString("DiaSemana"));
	            horario.setHoraInicioFin(rs.getString("HoraInicioFin"));
	            horario.setFechaInicio(rs.getDate("FechaInicio"));
	            horario.setFechaFin(rs.getDate("FechaFin"));
	            horario.setMaxEstudiantes(rs.getInt("MaxEstudiantes"));
	            horario.setModalidad(rs.getString("Modalidad"));
	            horario.setEstado(rs.getString("Estado"));
	            horario.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
	            horario.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
	            horario.setFechaActualizacion(rs.getTimestamp("FechaActualizacion"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (psm != null) psm.close();
	            if (cn != null) cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return horario;
	}

	@Override
	public int editarHorario(Horario horario) {
		int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "update horario set CursoID=?, ProfesorID=?, SeccionID=?, DiaSemana=?, HoraInicioFin=?, FechaInicio=?, FechaFin=?, MaxEstudiantes=?, Modalidad=?, Estado=?, FechaActualizacion=? WHERE HorarioID=?";
            psm = cn.prepareStatement(sql);
            
            psm.setInt(1, horario.getCursoID());
            psm.setInt(2, horario.getProfesorID());
            psm.setInt(3, horario.getSeccionID());
            psm.setString(4, horario.getDiaSemana());
            psm.setString(5, horario.getHoraInicioFin());
            psm.setDate(6, new java.sql.Date(horario.getFechaInicio().getTime()));
            psm.setDate(7, new java.sql.Date(horario.getFechaFin().getTime()));
            psm.setInt(8, horario.getMaxEstudiantes());
            psm.setString(9, horario.getModalidad());
            psm.setString(10, horario.getEstado());
            psm.setDate(11, new java.sql.Date(System.currentTimeMillis()));
            psm.setInt(12, horario.getHorarioID());
            
            value = psm.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(psm != null) psm.close();
                if(cn != null) cn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }        
        return value;
	}

	@Override
	public int eliminarHorario(int id) {
		int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Delete from horario where HorarioID=?";
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
