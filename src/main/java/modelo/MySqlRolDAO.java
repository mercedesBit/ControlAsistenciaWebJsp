package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Rol;
import interfaces.RolDAO;
import util.MySqlConexion;

public class MySqlRolDAO implements RolDAO{

	@Override
	public List<Rol> listRol() {
		List<Rol> roles=new ArrayList<Rol>();
		String sql="SELECT * FROM roles";
		try(Connection con=MySqlConexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Rol r=new Rol();
				r.setRoleID(rs.getInt("RoleID"));
				r.setNombreRole(rs.getString("NombreRole"));
				
				roles.add(r);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
		}

	@Override
	public int registrarRol(Rol rol) {
		int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Insert into roles values (?,?)";
            psm=cn.prepareStatement(sql);
            psm.setInt(1, rol.getRoleID());
            psm.setString(2, rol.getNombreRole());
            value=psm.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { 
                if(cn != null) cn.close();
                if(psm != null) psm.close();
            } catch(Exception e) {
                e.printStackTrace();
              }
            }return value;
	}

	@Override
	public Rol obtenerRol(int id) {
		Rol rol = null;
        Connection cn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Select * from roles where RoleID=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if(rs.next()) {
                rol = new Rol();
                rol.setRoleID(rs.getInt("RoleID"));
                rol.setNombreRole(rs.getString("NombreRole"));
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
        return rol;        
	}

	@Override
	public int editarRol(Rol rol) {
		int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Update roles set NombreRole=? where RoleID=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, rol.getRoleID());
            psm.setString(2, rol.getNombreRole());
            value = psm.executeUpdate();
        }catch (Exception e) {
        	 e.printStackTrace();
		}finally {
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
	public int eliminarRol(int id) {
		int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "Delete from roles where RoleID=?";
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


