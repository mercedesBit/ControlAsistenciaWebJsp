package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Estudiante;
import entidades.Horario;
import entidades.Matricula;
import interfaces.MatriculaInterface;
import util.MySqlConexion;

public class MatriculaModel implements MatriculaInterface{

	
	@Override
	public List<Matricula> listMatricula() {
		
	    List<Matricula> listMatricula = new ArrayList<Matricula>();
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;

	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "SELECT * FROM matricula";
	        psm = cn.prepareStatement(sql);
	        rs = psm.executeQuery();

	        while (rs.next()) {
	        	Matricula reg = new Matricula();
	            reg.setCodigoMatricula(rs.getString("CodigoMatricula"));

	            Estudiante estudiante = new Estudiante();
	            estudiante.setEstudianteID(rs.getInt("EstudianteID"));
	            reg.setEstudiante(estudiante);

	            Horario horario = new Horario();
	            horario.setCodHorario(rs.getInt("IdHorario"));;
	            reg.setHorario(horario);

	            reg.setFechaMatricula(rs.getDate("FechaMatricula"));
	            reg.setEstadoMatricula(rs.getString("EstadoMatricula"));
	            reg.setObservaciones(rs.getString("Observaciones"));
	            reg.setModoMatricula(rs.getString("ModoMatricula"));
	            reg.setCiclo(rs.getString("Ciclo"));

	            listMatricula.add(reg);
	        }
	    } catch (Exception e) {
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

	    return listMatricula;
	}

	@Override
	public int registrarMatricula(Matricula matricula) {
		 int value = 0;
		    Connection cn = null;
		    PreparedStatement psm = null;

		    try {
		        cn = MySqlConexion.getConexion();
		        String sql = "INSERT INTO matricula (CodigoMatricula, EstudianteID, IdHorario, FechaMatricula, EstadoMatricula, Observaciones, ModoMatricula, Ciclo) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		        psm = cn.prepareStatement(sql);

		        psm.setString(1, matricula.getCodigoMatricula());
		        psm.setInt(2, matricula.getEstudiante().getEstudianteID());
		        psm.setInt(3, matricula.getHorario().getCodHorario());
		        psm.setDate(4, new java.sql.Date(matricula.getFechaMatricula().getTime()));
		        psm.setString(5, matricula.getEstadoMatricula());
		        psm.setString(6, matricula.getObservaciones());
		        psm.setString(7, matricula.getModoMatricula());
		        psm.setString(8, matricula.getCiclo());

		        value = psm.executeUpdate();
		    } catch (Exception e) {
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

}
