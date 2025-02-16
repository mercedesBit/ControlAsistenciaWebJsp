package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Estudiante;
import entidades.Horario;
import entidades.Matricula;
import interfaces.MatriculaInterface;
import util.MySqlConexion;

public class MatriculaModel implements MatriculaInterface {

	@Override
	public List<Matricula> listMatricula() {

		List<Matricula> listMatricula = new ArrayList<Matricula>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;

		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT m.codigo_matricula,c.NombreCurso,p.Nombres as nomProf , p.Apellidos as apeProf,  e.nombres, e.apellidos, h.DiaSemana as Horario, m.fecha_matricula, m.estado_matricula, m.observaciones, m.modo_matricula, m.ciclo \r\n"
					+ " FROM matricula m\r\n" + "	JOIN estudiante e ON m.id_estudiante = e.EstudianteID\r\n"
					+ "JOIN horario h ON m.id_horario = h.HorarioID\r\n" + "JOIN curso c ON c.CursoID = h.CursoID\r\n"
					+ "JOIN profesor p ON p.ProfesorID = h.ProfesorID ";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();

			while (rs.next()) {
				Matricula reg = new Matricula();
				reg.setCodigoMatricula(rs.getString("codigo_matricula"));
				Estudiante estudiante = new Estudiante();
				estudiante.setNombres(rs.getString("nombres"));
				estudiante.setApellidos(rs.getString("apellidos"));
				reg.setEstudiante(estudiante);

				Horario horario = new Horario();
				horario.setDiaSemana(rs.getString("Horario"));
				horario.setNombreCurso(rs.getString("NombreCurso"));
				horario.setNombreProfesor(rs.getString("nomProf"));
				horario.setApellidoProfesor(rs.getString("apeProf"));
				reg.setHorario(horario);

				reg.setFechaMatricula(rs.getDate("fecha_matricula"));
				reg.setEstadoMatricula(rs.getString("estado_matricula"));
				reg.setObservaciones(rs.getString("observaciones"));
				reg.setModoMatricula(rs.getString("modo_matricula"));
				reg.setCiclo(rs.getString("ciclo"));

				listMatricula.add(reg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cn != null)
					cn.close();
				if (psm != null)
					psm.close();
			} catch (Exception e) {
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
			String sql = "INSERT INTO matricula (Codigo_Matricula, id_Estudiante, id_Horario, Fecha_Matricula, Estado_Matricula, Observaciones, Modo_Matricula, Ciclo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			psm = cn.prepareStatement(sql);

			psm.setString(1, matricula.getCodigoMatricula());
			psm.setInt(2, matricula.getEstudiante().getEstudianteID());
			psm.setInt(3, matricula.getHorario().getHorarioID());
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
				if (cn != null)
					cn.close();
				if (psm != null)
					psm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

}
