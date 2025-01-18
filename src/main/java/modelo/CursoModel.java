package modelo;

import entidades.Curso;
import interfaces.CursoInterface;
import util.MySqlConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoModel implements CursoInterface {
    private Connection conn;

    @Override
    public int registrar(Curso curso) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "INSERT INTO Curso (CursoID,CodigoCurso, NombreCurso, Descripcion, Duracion, Grado, Nivel, FechaInicio, FechaFin, Estado, RequisitosPrevios, CantidadMaximaEstudiantes, Modalidad, SeccionID, Temario, Horario, Notas, ProfesorID, FechaRegistro, UsuarioRegistro) " +
                         "VALUES (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, curso.getCodigoCurso());
            pst.setString(2, curso.getNombreCurso());
            pst.setString(3, curso.getDescripcion());
            pst.setInt(4, curso.getDuracion());
            pst.setString(5, curso.getGrado());
            pst.setString(6, curso.getNivel());
            pst.setDate(7, new java.sql.Date(curso.getFechaInicio().getTime()));
            pst.setDate(8, new java.sql.Date(curso.getFechaFin().getTime()));
            pst.setString(9, curso.getEstado());
            pst.setString(10, curso.getRequisitosPrevios());
            pst.setInt(11, curso.getCantidadMaximaEstudiantes());
            pst.setString(12, curso.getModalidad());
            pst.setInt(13, curso.getSeccionID());
            pst.setString(14, curso.getTemario());
            pst.setString(15, curso.getHorario());
            pst.setString(16, curso.getNotas());
            pst.setInt(17, curso.getProfesorID());
            pst.setDate(18, new java.sql.Date(curso.getFechaRegistro().getTime()));
            pst.setString(19, curso.getUsuarioRegistro());

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
    public int actualizar(Curso curso) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "UPDATE Curso SET CodigoCurso=?, NombreCurso=?, Descripcion=?, Duracion=?, Grado=?, Nivel=?, FechaInicio=?, FechaFin=?, Estado=?, RequisitosPrevios=?, CantidadMaximaEstudiantes=?, Modalidad=?, SeccionID=?, Temario=?, Horario=?, Notas=?, ProfesorID=?, FechaActualizacion=? WHERE CursoID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, curso.getCodigoCurso());
            pst.setString(2, curso.getNombreCurso());
            pst.setString(3, curso.getDescripcion());
            pst.setInt(4, curso.getDuracion());
            pst.setString(5, curso.getGrado());
            pst.setString(6, curso.getNivel());
            pst.setDate(7, new java.sql.Date(curso.getFechaInicio().getTime()));
            pst.setDate(8, new java.sql.Date(curso.getFechaFin().getTime()));
            pst.setString(9, curso.getEstado());
            pst.setString(10, curso.getRequisitosPrevios());
            pst.setInt(11, curso.getCantidadMaximaEstudiantes());
            pst.setString(12, curso.getModalidad());
            pst.setInt(13, curso.getSeccionID());
            pst.setString(14, curso.getTemario());
            pst.setString(15, curso.getHorario());
            pst.setString(16, curso.getNotas());
            pst.setInt(17, curso.getProfesorID());
            pst.setDate(18, new java.sql.Date(curso.getFechaActualizacion().getTime()));
            pst.setInt(19, curso.getCursoID());

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
    public int eliminar(int cursoID) {
        int resultado = 0;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "DELETE FROM Curso WHERE CursoID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, cursoID);

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
    public Curso obtenerPorID(int cursoID) {
        Curso curso = null;
        try {
            conn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Curso WHERE CursoID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, cursoID);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                curso = new Curso();
                curso.setCursoID(rs.getInt("CursoID"));
                curso.setCodigoCurso(rs.getString("CodigoCurso"));
                curso.setNombreCurso(rs.getString("NombreCurso"));
                curso.setDescripcion(rs.getString("Descripcion"));
                curso.setDuracion(rs.getInt("Duracion"));
                curso.setGrado(rs.getString("Grado"));
                curso.setNivel(rs.getString("Nivel"));
                curso.setFechaInicio(rs.getDate("FechaInicio"));
                curso.setFechaFin(rs.getDate("FechaFin"));
                curso.setEstado(rs.getString("Estado"));
                curso.setRequisitosPrevios(rs.getString("RequisitosPrevios"));
                curso.setCantidadMaximaEstudiantes(rs.getInt("CantidadMaximaEstudiantes"));
                curso.setModalidad(rs.getString("Modalidad"));
                curso.setSeccionID(rs.getInt("SeccionID"));
                curso.setTemario(rs.getString("Temario"));
                curso.setHorario(rs.getString("Horario"));
                curso.setNotas(rs.getString("Notas"));
                curso.setProfesorID(rs.getInt("ProfesorID"));
                curso.setFechaRegistro(rs.getDate("FechaRegistro"));
                curso.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                curso.setFechaActualizacion(rs.getDate("FechaActualizacion"));
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
        return curso;
    }

    @Override
    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        try {
            conn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Curso";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCursoID(rs.getInt("CursoID"));
                curso.setCodigoCurso(rs.getString("CodigoCurso"));
                curso.setNombreCurso(rs.getString("NombreCurso"));
                curso.setDescripcion(rs.getString("Descripcion"));
                curso.setDuracion(rs.getInt("Duracion"));
                curso.setGrado(rs.getString("Grado"));
                curso.setNivel(rs.getString("Nivel"));
                curso.setFechaInicio(rs.getDate("FechaInicio"));
                curso.setFechaFin(rs.getDate("FechaFin"));
                curso.setEstado(rs.getString("Estado"));
                curso.setRequisitosPrevios(rs.getString("RequisitosPrevios"));
                curso.setCantidadMaximaEstudiantes(rs.getInt("CantidadMaximaEstudiantes"));
                curso.setModalidad(rs.getString("Modalidad"));
                curso.setSeccionID(rs.getInt("SeccionID"));
                curso.setTemario(rs.getString("Temario"));
                curso.setHorario(rs.getString("Horario"));
                curso.setNotas(rs.getString("Notas"));
                curso.setProfesorID(rs.getInt("ProfesorID"));
                curso.setFechaRegistro(rs.getDate("FechaRegistro"));
                curso.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                curso.setFechaActualizacion(rs.getDate("FechaActualizacion"));
                lista.add(curso);
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
