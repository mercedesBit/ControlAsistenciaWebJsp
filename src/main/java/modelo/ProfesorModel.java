package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Profesor;
import interfaces.ProfesorInterface;
import util.MySqlConexion;

public class ProfesorModel implements ProfesorInterface {
	
	@Override
	public List<Profesor> listProfesor() {
	    List<Profesor> listProfesor = new ArrayList<Profesor>();
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Select * from profesor";
	        psm = cn.prepareStatement(sql);
	        rs = psm.executeQuery();
	        
	        while(rs.next()) {
	            Profesor reg = new Profesor();
	            reg.setProfesorID(rs.getInt("ProfesorID"));
	            reg.setTipoDocumento(rs.getString("TipoDocumento"));
	            reg.setNumeroDocumento(rs.getString("NumeroDocumento"));
	            reg.setNombres(rs.getString("Nombres"));
	            reg.setApellidos(rs.getString("Apellidos"));
	            reg.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            reg.setCorreoInstitucional(rs.getString("CorreoInstitucional"));
	            reg.setTelefonoMovil(rs.getString("TelefonoMovil"));
	            reg.setTelefonoTrabajo(rs.getString("TelefonoTrabajo"));
	            reg.setFechaContratacion(rs.getDate("FechaContratacion"));
	            reg.setEspecialidad(rs.getString("Especialidad"));
	            reg.setGradoAcademico(rs.getString("GradoAcademico"));
	            reg.setHorasDocencia(rs.getInt("HorasDocencia"));
	            reg.setEstado(rs.getString("Estado"));
	            reg.setDireccion(rs.getString("Direccion"));
	            reg.setModalidadTrabajo(rs.getString("ModalidadTrabajo"));
	            reg.setRedesSociales(rs.getString("RedesSociales"));
	            reg.setFechaRegistro(rs.getDate("FechaRegistro"));
	            reg.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
	            reg.setFechaActualizacion(rs.getDate("FechaActualizacion"));
	            listProfesor.add(reg);
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
	            
	    return listProfesor;
	}

	@Override
	public int registrarProfesor(Profesor profesor) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Insert into profesor values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, profesor.getTipoDocumento());
	        psm.setString(2, profesor.getNumeroDocumento());
	        psm.setString(3, profesor.getNombres());
	        psm.setString(4, profesor.getApellidos());
	        psm.setDate(5, new java.sql.Date(profesor.getFechaNacimiento().getTime()));
	        psm.setString(6, profesor.getCorreoInstitucional());
	        psm.setString(7, profesor.getTelefonoMovil());
	        psm.setString(8, profesor.getTelefonoTrabajo());
	        psm.setDate(9, new java.sql.Date(profesor.getFechaContratacion().getTime()));
	        psm.setString(10, profesor.getEspecialidad());
	        psm.setString(11, profesor.getGradoAcademico());
	        psm.setInt(12, profesor.getHorasDocencia());
	        psm.setString(13, profesor.getEstado());
	        psm.setString(14, profesor.getDireccion());
	        psm.setString(15, profesor.getModalidadTrabajo());
	        psm.setString(16, profesor.getRedesSociales());
	        psm.setDate(17, new java.sql.Date(System.currentTimeMillis()));
	        psm.setString(18, profesor.getUsuarioRegistro());
	        psm.setDate(19, new java.sql.Date(System.currentTimeMillis()));
	        
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
	public Profesor obtenerProfesor(int id) {
	    Profesor profesor = null;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Select * from profesor where ProfesorID=?";
	        psm = cn.prepareStatement(sql);
	        psm.setInt(1, id);
	        rs = psm.executeQuery();
	        if(rs.next()) {
	            profesor = new Profesor();
	            profesor.setProfesorID(rs.getInt("ProfesorID"));
	            profesor.setTipoDocumento(rs.getString("TipoDocumento"));
	            profesor.setNumeroDocumento(rs.getString("NumeroDocumento"));
	            profesor.setNombres(rs.getString("Nombres"));
	            profesor.setApellidos(rs.getString("Apellidos"));
	            profesor.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            profesor.setCorreoInstitucional(rs.getString("CorreoInstitucional"));
	            profesor.setTelefonoMovil(rs.getString("TelefonoMovil"));
	            profesor.setTelefonoTrabajo(rs.getString("TelefonoTrabajo"));
	            profesor.setFechaContratacion(rs.getDate("FechaContratacion"));
	            profesor.setEspecialidad(rs.getString("Especialidad"));
	            profesor.setGradoAcademico(rs.getString("GradoAcademico"));
	            profesor.setHorasDocencia(rs.getInt("HorasDocencia"));
	            profesor.setEstado(rs.getString("Estado"));
	            profesor.setDireccion(rs.getString("Direccion"));
	            profesor.setModalidadTrabajo(rs.getString("ModalidadTrabajo"));
	            profesor.setRedesSociales(rs.getString("RedesSociales"));
	            profesor.setFechaRegistro(rs.getDate("FechaRegistro"));
	            profesor.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
	            profesor.setFechaActualizacion(rs.getDate("FechaActualizacion"));
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
	    return profesor;                
	}

	@Override
	public int editarProfesor(Profesor profesor) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Update profesor set TipoDocumento=?, NumeroDocumento=?, Nombres=?, Apellidos=?, FechaNacimiento=?, CorreoInstitucional=?, TelefonoMovil=?, TelefonoTrabajo=?, FechaContratacion=?, Especialidad=?, GradoAcademico=?, HorasDocencia=?, Estado=?, Direccion=?, ModalidadTrabajo=?, RedesSociales=?, FechaRegistro=?, UsuarioRegistro=? where ProfesorID=?";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, profesor.getTipoDocumento());
	        psm.setString(2, profesor.getNumeroDocumento());
	        psm.setString(3, profesor.getNombres());
	        psm.setString(4, profesor.getApellidos());
	        psm.setDate(5, new java.sql.Date(profesor.getFechaNacimiento().getTime()));
	        psm.setString(6, profesor.getCorreoInstitucional());
	        psm.setString(7, profesor.getTelefonoMovil());
	        psm.setString(8, profesor.getTelefonoTrabajo());
	        psm.setDate(9, new java.sql.Date(profesor.getFechaContratacion().getTime()));
	        psm.setString(10, profesor.getEspecialidad());
	        psm.setString(11, profesor.getGradoAcademico());
	        psm.setInt(12, profesor.getHorasDocencia());
	        psm.setString(13, profesor.getEstado());
	        psm.setString(14, profesor.getDireccion());
	        psm.setString(15, profesor.getModalidadTrabajo());
	        psm.setString(16, profesor.getRedesSociales());
	        psm.setDate(17, new java.sql.Date(System.currentTimeMillis()));
	        psm.setString(18, profesor.getUsuarioRegistro());
	        psm.setInt(19, profesor.getProfesorID());
	        
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
	public int eliminarProfesor(int id) {
	    int value = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    
	    try {
	        cn = MySqlConexion.getConexion();
	        String sql = "Delete from profesor where ProfesorID=?";
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