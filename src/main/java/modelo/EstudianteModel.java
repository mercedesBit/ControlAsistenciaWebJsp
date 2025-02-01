package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Estudiante;
import entidades.PadresTutores;
import interfaces.EstudianteInterface;
import util.MySqlConexion;

public class EstudianteModel implements EstudianteInterface{
	
	@Override
	public List<Estudiante> listEstudiante() {
	    List<Estudiante> listEstudiante = new ArrayList<Estudiante>();
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Select * from estudiante";
	        psm = cn.prepareStatement(sql);
	        rs = psm.executeQuery();
	        
	        while(rs.next()) {
	            Estudiante reg = new Estudiante();
	            reg.setEstudianteID(rs.getInt("EstudianteID"));
	            reg.setTipoDocumento(rs.getString("TipoDocumento"));
	            reg.setNumeroDocumento(rs.getString("NumeroDocumento"));
	            reg.setNombres(rs.getString("Nombres"));
	            reg.setApellidos(rs.getString("Apellidos"));
	            reg.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            reg.setGenero(rs.getString("Genero"));
	            reg.setEmail(rs.getString("Email"));
	            reg.setTelefono(rs.getString("Telefono"));
	            reg.setDireccion(rs.getString("Direccion"));
	            reg.setEstadoCivil(rs.getString("EstadoCivil"));
	            reg.setFechaIngreso(rs.getDate("FechaIngreso"));
	            reg.setEstado(rs.getString("Estado"));
	            reg.setRedesSociales(rs.getString("RedesSociales"));
	            reg.setEmergenciaContacto(rs.getString("EmergenciaContacto"));
	            PadresTutores tutor= new PadresTutores();
	            tutor.setTutorID(rs.getInt("TutorID"));
	            reg.setTutor(tutor);
	            reg.setFechaRegistro(rs.getDate("FechaRegistro"));
	            reg.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
	            reg.setFechaActualizacion(rs.getDate("FechaActualizacion"));
	            listEstudiante.add(reg);
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
	            
	    return listEstudiante;
	}

	@Override
	public int registrarEstudiante(Estudiante estudiante) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Insert into estudiante values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, estudiante.getTipoDocumento());
	        psm.setString(2, estudiante.getNumeroDocumento());
	        psm.setString(3, estudiante.getNombres());
	        psm.setString(4, estudiante.getApellidos());
	        psm.setDate(5, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
	        psm.setString(6, estudiante.getGenero());
	        psm.setString(7, estudiante.getEmail());
	        psm.setString(8, estudiante.getTelefono());
	        psm.setString(9, estudiante.getDireccion());
	        psm.setString(10, estudiante.getEstadoCivil());
	        psm.setDate(11, new java.sql.Date(estudiante.getFechaIngreso().getTime()));
	        psm.setString(12, estudiante.getEstado());
	        psm.setString(13, estudiante.getRedesSociales());
	        psm.setString(14, estudiante.getEmergenciaContacto());
	        psm.setInt(15, estudiante.getTutor().getTutorID());
	        psm.setDate(16, new java.sql.Date(System.currentTimeMillis()));
	        psm.setString(17, estudiante.getUsuarioRegistro());
	        psm.setDate(18, null);

	        value = psm.executeUpdate();
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
	public Estudiante obtenerEstudiante(int id) {
	    Estudiante estudiante = null;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Select * from estudiante where EstudianteID=?";
	        psm = cn.prepareStatement(sql);
	        psm.setInt(1, id);
	        rs = psm.executeQuery();
	        if(rs.next()) {
	            estudiante = new Estudiante();
	            estudiante.setEstudianteID(rs.getInt("EstudianteID"));
	            estudiante.setTipoDocumento(rs.getString("TipoDocumento"));
	            estudiante.setNumeroDocumento(rs.getString("NumeroDocumento"));
	            estudiante.setNombres(rs.getString("Nombres"));
	            estudiante.setApellidos(rs.getString("Apellidos"));
	            estudiante.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            estudiante.setGenero(rs.getString("Genero"));
	            estudiante.setEmail(rs.getString("Email"));
	            estudiante.setTelefono(rs.getString("Telefono"));
	            estudiante.setDireccion(rs.getString("Direccion"));
	            estudiante.setEstadoCivil(rs.getString("EstadoCivil"));
	            estudiante.setFechaIngreso(rs.getDate("FechaIngreso"));
	            estudiante.setEstado(rs.getString("Estado"));
	            estudiante.setRedesSociales(rs.getString("RedesSociales"));
	            estudiante.setEmergenciaContacto(rs.getString("EmergenciaContacto"));
	            
	            PadresTutores tutor= new PadresTutores();
	            tutor.setTutorID(rs.getInt("TutorID"));
	            
	            estudiante.setTutor(tutor);
	            estudiante.setFechaRegistro(rs.getDate("FechaRegistro"));
	            estudiante.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
	            estudiante.setFechaActualizacion(rs.getDate("FechaActualizacion"));
	        }            
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(psm != null) psm.close();
	            if(rs != null) rs.close();
	            if(cn != null) cn.close();
	        } catch(Exception e) {                
	            e.printStackTrace();
	        }
	    }
	    return estudiante;                
	}

	@Override
	public int editarEstudiante(Estudiante estudiante) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Update estudiante set TipoDocumento=?, NumeroDocumento=?, Nombres=?, Apellidos=?, FechaNacimiento=?, Genero=?, Email=?, Telefono=?, Direccion=?, EstadoCivil=?, FechaIngreso=?, Estado=?, RedesSociales=?, EmergenciaContacto=?, TutorID=?, FechaRegistro=?, UsuarioRegistro=?,FechaActualizacion=? where EstudianteID=?";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, estudiante.getTipoDocumento());
	        psm.setString(2, estudiante.getNumeroDocumento());
	        psm.setString(3, estudiante.getNombres());
	        psm.setString(4, estudiante.getApellidos());
	        psm.setDate(5, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
	        psm.setString(6, estudiante.getGenero());
	        psm.setString(7, estudiante.getEmail());
	        psm.setString(8, estudiante.getTelefono());
	        psm.setString(9, estudiante.getDireccion());
	        psm.setString(10, estudiante.getEstadoCivil());
	        psm.setDate(11, new java.sql.Date(estudiante.getFechaIngreso().getTime()));
	        psm.setString(12, estudiante.getEstado());
	        psm.setString(13, estudiante.getRedesSociales());
	        psm.setString(14, estudiante.getEmergenciaContacto());
	        psm.setInt(15, estudiante.getTutor().getTutorID());
	        psm.setDate(16, new java.sql.Date(System.currentTimeMillis()));
	        psm.setString(17, estudiante.getUsuarioRegistro());
	        psm.setDate(18,  new java.sql.Date(System.currentTimeMillis()));
	        psm.setInt(19, estudiante.getEstudianteID());

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
	public int eliminarEstudiante(int id) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Delete from estudiante where EstudianteID=?";
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
