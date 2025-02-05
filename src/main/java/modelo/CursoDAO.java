package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Curso;
import interfaces.Curso_DAO_interface;
import util.MySqlConexion;

public class CursoDAO implements Curso_DAO_interface {

    @Override
    public List<Curso> obtenerTodosLosCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        try (Connection con = MySqlConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setCursoID(rs.getInt("cursoID"));
                c.setCodigoCurso(rs.getString("codigoCurso"));
                c.setNombreCurso(rs.getString("nombreCurso"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setCreditos(rs.getInt("creditos"));
                c.setCiclo(rs.getString("ciclo"));
                c.setNivel(rs.getString("nivel"));
                
         
                c.setEstado(rs.getString("estado"));
                
                /*
                 *    //    c.setFechaInicio(rs.getDate("fechaInicio"));
             //   c.setFechaFin(rs.getDate("fechaFin"));
                c.setRequisitosPrevios(rs.getString("requisitosPrevios"));
                c.setCantidadMaximaEstudiantes(rs.getInt("cantidadMaximaEstudiantes"));
                c.setModalidad(rs.getString("modalidad"));
           //     c.setSeccionID(rs.getInt("seccionID"));
            //    c.setTemario(rs.getString("temario"));
            //    c.setHorario(rs.getString("horario"));
             * //    c.setProfesorID(rs.getInt("profesorID"));
             * */
             
                c.setNotas(rs.getString("notas"));
            
                c.setFechaRegistro(rs.getDate("fechaRegistro"));
                c.setUsuarioRegistro(rs.getString("usuarioRegistro"));
                c.setFechaActualizacion(rs.getDate("fechaActualizacion"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
