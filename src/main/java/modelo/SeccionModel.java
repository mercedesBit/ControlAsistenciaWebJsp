package modelo;

import entidades.Seccion;
import interfaces.SeccionInterface;
import util.MySqlConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeccionModel implements SeccionInterface {
    private Connection conn;

    @Override
    public int registrar(Seccion seccion) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "INSERT INTO Seccion (NombreSeccion, NumeroAula, Estado, FechaRegistro, UsuarioRegistro) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, seccion.getNombreSeccion());
            pst.setString(2, seccion.getNumeroAula());
            pst.setString(3, seccion.getEstado());
            pst.setDate(4, new java.sql.Date(seccion.getFechaRegistro().getTime()));
            pst.setString(5, seccion.getUsuarioRegistro());

            resultado = pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Seccion seccion) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "UPDATE Seccion SET NombreSeccion=?, NumeroAula=?, Estado=?, " +
                         "FechaActualizacion=? WHERE SeccionID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, seccion.getNombreSeccion());
            pst.setString(2, seccion.getNumeroAula());
            pst.setString(3, seccion.getEstado());
            pst.setDate(4, new java.sql.Date(seccion.getFechaActualizacion().getTime()));
            pst.setInt(5, seccion.getSeccionID());

            resultado = pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int seccionID) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "DELETE FROM Seccion WHERE SeccionID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, seccionID);

            resultado = pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public Seccion obtenerPorID(int seccionID) {
        Seccion seccion = null;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Seccion WHERE SeccionID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, seccionID);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                seccion = new Seccion();
                seccion.setSeccionID(rs.getInt("SeccionID"));
                seccion.setNombreSeccion(rs.getString("NombreSeccion"));
                seccion.setNumeroAula(rs.getString("NumeroAula"));
                seccion.setEstado(rs.getString("Estado"));
                seccion.setFechaRegistro(rs.getDate("FechaRegistro"));
                seccion.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                seccion.setFechaActualizacion(rs.getDate("FechaActualizacion"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seccion;
    }

    @Override
    public List<Seccion> listarTodos() {
        List<Seccion> lista = new ArrayList<>();
        try {
            conn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Seccion";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Seccion seccion = new Seccion();
                seccion.setSeccionID(rs.getInt("SeccionID"));
                seccion.setNombreSeccion(rs.getString("NombreSeccion"));
                seccion.setNumeroAula(rs.getString("NumeroAula"));
                seccion.setEstado(rs.getString("Estado"));
                seccion.setFechaRegistro(rs.getDate("FechaRegistro"));
                seccion.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                seccion.setFechaActualizacion(rs.getDate("FechaActualizacion"));
                lista.add(seccion);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}