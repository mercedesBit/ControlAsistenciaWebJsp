package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.PadresTutores;
import interfaces.PadresTutoresInterface;
import util.MySqlConexion;

public class PadresTutoresModel implements PadresTutoresInterface{

    @Override
    public List<PadresTutores> listPadresTutores() {
        List<PadresTutores> listPadresTutores = new ArrayList<PadresTutores>();
        Connection cn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Select * from PadresTutores";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();

            while (rs.next()) {
                PadresTutores reg = new PadresTutores();
                reg.setTutorID(rs.getInt("TutorID"));
                reg.setTipoDocumento(rs.getString("TipoDocumento"));
                reg.setNumeroDocumento(rs.getString("NumeroDocumento"));
                reg.setNombres(rs.getString("Nombres"));
                reg.setApellidos(rs.getString("Apellidos"));
                reg.setCorreoElectronico(rs.getString("CorreoElectronico"));
                reg.setTelefonoMovil(rs.getString("TelefonoMovil"));
                reg.setTelefonoCasa(rs.getString("TelefonoCasa"));
                reg.setDireccion(rs.getString("Direccion"));
                reg.setRelacionEstudiante(rs.getString("RelacionEstudiante"));
                reg.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                reg.setOcupacion(rs.getString("Ocupacion"));
                reg.setEstado(rs.getString("Estado"));
                reg.setRedesSociales(rs.getString("RedesSociales"));
                reg.setFechaRegistro(rs.getDate("FechaRegistro"));
                reg.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                reg.setFechaActualizacion(rs.getDate("FechaActualizacion"));
                listPadresTutores.add(reg);
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

        return listPadresTutores;
    }

    @Override
    public int registrarPadresTutores(PadresTutores tutor) {
        int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Insert into PadresTutores values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, tutor.getTutorID());
            psm.setString(2, tutor.getTipoDocumento());
            psm.setString(3, tutor.getNumeroDocumento());
            psm.setString(4, tutor.getNombres());
            psm.setString(5, tutor.getApellidos());
            psm.setString(6, tutor.getCorreoElectronico());
            psm.setString(7, tutor.getTelefonoMovil());
            psm.setString(8, tutor.getTelefonoCasa());
            psm.setString(9, tutor.getDireccion());
            psm.setString(10, tutor.getRelacionEstudiante());
            psm.setDate(11, new java.sql.Date(tutor.getFechaNacimiento().getTime()));
            psm.setString(12, tutor.getOcupacion());
            psm.setString(13, tutor.getEstado());
            psm.setString(14, tutor.getRedesSociales());
            psm.setDate(15, new java.sql.Date(System.currentTimeMillis()));
            psm.setString(16, tutor.getUsuarioRegistro());
            psm.setDate(17, null);

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
    public PadresTutores obtenerPadresTutores(int id) {
        PadresTutores tutor = null;
        Connection cn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Select * from PadresTutores where TutorID=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if(rs.next()) {
                tutor = new PadresTutores();
                tutor.setTutorID(rs.getInt("TutorID"));
                tutor.setTipoDocumento(rs.getString("TipoDocumento"));
                tutor.setNumeroDocumento(rs.getString("NumeroDocumento"));
                tutor.setNombres(rs.getString("Nombres"));
                tutor.setApellidos(rs.getString("Apellidos"));
                tutor.setCorreoElectronico(rs.getString("CorreoElectronico"));
                tutor.setTelefonoMovil(rs.getString("TelefonoMovil"));
                tutor.setTelefonoCasa(rs.getString("TelefonoCasa"));
                tutor.setDireccion(rs.getString("Direccion"));
                tutor.setRelacionEstudiante(rs.getString("RelacionEstudiante"));
                tutor.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                tutor.setOcupacion(rs.getString("Ocupacion"));
                tutor.setEstado(rs.getString("Estado"));
                tutor.setRedesSociales(rs.getString("RedesSociales"));
                tutor.setFechaRegistro(rs.getDate("FechaRegistro"));
                tutor.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                tutor.setFechaActualizacion(rs.getDate("FechaActualizacion"));
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
        return tutor;                
    }

    @Override
    public int editarPadresTutores(PadresTutores tutor) {
        int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Update PadresTutores set TipoDocumento=?, NumeroDocumento=?, Nombres=?, Apellidos=?, CorreoElectronico=?, TelefonoMovil=?, TelefonoCasa=?, Direccion=?, RelacionEstudiante=?, FechaNacimiento=?, Ocupacion=?, Estado=?, RedesSociales=?, FechaRegistro=?, UsuarioRegistro=?, FechaActualizacion=? where TutorID=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, tutor.getTipoDocumento());
            psm.setString(2, tutor.getNumeroDocumento());
            psm.setString(3, tutor.getNombres());
            psm.setString(4, tutor.getApellidos());
            psm.setString(5, tutor.getCorreoElectronico());
            psm.setString(6, tutor.getTelefonoMovil());
            psm.setString(7, tutor.getTelefonoCasa());
            psm.setString(8, tutor.getDireccion());
            psm.setString(9, tutor.getRelacionEstudiante());
            psm.setDate(10, new java.sql.Date(tutor.getFechaNacimiento().getTime()));
            psm.setString(11, tutor.getOcupacion());
            psm.setString(12, tutor.getEstado());
            psm.setString(13, tutor.getRedesSociales());
            psm.setDate(14, new java.sql.Date(tutor.getFechaRegistro().getTime()));
            psm.setString(15, tutor.getUsuarioRegistro());
            psm.setDate(16, new java.sql.Date(System.currentTimeMillis()));
            psm.setInt(17, tutor.getTutorID());

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
    public int eliminarPadresTutores(int id) {
        int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Delete from PadresTutores where TutorID=?";
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
