package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.AsistenciaEstudiante;
import entidades.Curso;
import entidades.Estudiante;
import entidades.Horario;
import entidades.HorarioEstudiante;
import interfaces.AsistenciaEstudianteDAO;
import util.MySqlConexion;

public class AsistenciaEstudianteModel implements AsistenciaEstudianteDAO {

	Connection conexion;

//LISTA DE ESTUDIANTE DEPENDIENDO EL HORARIO 
	@Override
	public List<HorarioEstudiante> obtenerEstudiantesPorHorario(int horarioID) {
		List<HorarioEstudiante> listaHorarioEstudiante = new ArrayList<>();
		String query = "SELECT EstudianteID, Nombres, Apellidos FROM horario_estudiante WHERE HorarioID = ?";

		try (Connection conn = MySqlConexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, horarioID);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					HorarioEstudiante horarioEstudiante = new HorarioEstudiante();
					horarioEstudiante.setEstudianteID(rs.getInt("EstudianteID")); // Asigna el ID
					horarioEstudiante.setNombre(rs.getString("Nombres")); // Asigna el Nombre
					horarioEstudiante.setApellido(rs.getString("Apellidos")); // Asigna el Apellido

					listaHorarioEstudiante.add(horarioEstudiante);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaHorarioEstudiante;
	}

	// LISTA DE HORARIO DE ESTUDIANTE
	@Override
	public List<HorarioEstudiante> listHorario() {
		List<HorarioEstudiante> listHorario = new ArrayList<HorarioEstudiante>();

		PreparedStatement psm = null;
		ResultSet rs = null;

		try {
			conexion = MySqlConexion.getConexion();
			String sql = "Select * from horario_estudiante";
			psm = conexion.prepareStatement(sql);
			rs = psm.executeQuery();

			while (rs.next()) {
				HorarioEstudiante reg = new HorarioEstudiante();
				reg.setEstudianteID(rs.getInt("EstudianteID"));
				reg.setEstudianteID(rs.getInt("HorarioID"));

				listHorario.add(reg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conexion != null)
					conexion.close();
				if (psm != null)
					psm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listHorario;
	}

	@Override
	public List<AsistenciaEstudiante> obtenerAsistencia(String horarioIDParam, String estudianteIDParam) {
		List<AsistenciaEstudiante> listaAsistencia = new ArrayList<>();

		PreparedStatement psm = null;
		ResultSet rs = null;

		if (horarioIDParam == null || horarioIDParam.isEmpty()) {
			System.out.println("Error: horarioID es obligatorio en obtenerAsistencia.");
			return listaAsistencia;
		}

		try {
			int horarioID = Integer.parseInt(horarioIDParam);
			Integer estudianteID = null;
			if (estudianteIDParam != null && !estudianteIDParam.isEmpty()) {
				estudianteID = Integer.parseInt(estudianteIDParam);
			}

			conexion = MySqlConexion.getConexion();
			String sql = "SELECT * FROM asistencia_estudiante WHERE HorarioID = ? "
					+ (estudianteID != null ? "AND EstudianteID = ? " : "") + "ORDER BY FECHADECLASE ASC";

			psm = conexion.prepareStatement(sql);
			psm.setInt(1, horarioID);
			if (estudianteID != null) {
				psm.setInt(2, estudianteID);
			}
			rs = psm.executeQuery();

			while (rs.next()) {
				AsistenciaEstudiante asistencia = new AsistenciaEstudiante();
				asistencia.setAsistenciaID(rs.getInt("AsistenciaID"));
				asistencia.setEstudianteID(rs.getInt("EstudianteID"));
				asistencia.setHorarioID(rs.getInt("HorarioID"));
				asistencia.setEstadoAsistencia(rs.getString("EstadoAsistencia"));
				asistencia.setComentario(rs.getString("Comentario"));
				asistencia.setFechaDeClase(rs.getDate("FECHADECLASE"));
				asistencia.setDiaAsistencia(rs.getString("DIAASISTENCIA"));
				asistencia.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
				asistencia.setFechaActualizacion(rs.getDate("FechaActualizacion"));

				listaAsistencia.add(asistencia);
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: No se pudo convertir algún ID a número entero en obtenerAsistencia.");
		} catch (SQLException e) {
			System.out.println("Error en la consulta SQL: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psm != null)
					psm.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaAsistencia;
	}

	@Override
	public AsistenciaEstudiante obtenerPorID(int asistenciaID) {
		AsistenciaEstudiante asistenciaEstudiante = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = MySqlConexion.getConexion();
			String sql = "{CALL ObtenerAsistenciaxID(?)}";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, asistenciaID);
			rs = pst.executeQuery();

			if (rs.next()) {
				asistenciaEstudiante = new AsistenciaEstudiante();
				asistenciaEstudiante.setAsistenciaID(rs.getInt("AsistenciaID"));
				asistenciaEstudiante.setEstudianteID(rs.getInt("EstudianteID"));
				asistenciaEstudiante.setComentario(rs.getString("Comentario"));
				asistenciaEstudiante.setDiaAsistencia(rs.getString("DIAASISTENCIA"));
				asistenciaEstudiante.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
				asistenciaEstudiante.setFechaDeClase(rs.getDate("FECHADECLASE"));

				asistenciaEstudiante.setEstadoAsistencia(rs.getString("EstadoAsistencia"));
				asistenciaEstudiante.setFechaActualizacion(rs.getDate("FECHAACTUALIZACION"));
				asistenciaEstudiante.setHorarioID(rs.getInt("HorarioID"));

				// Inicializar y asignar datos a Estudiante
				Estudiante estudiante = new Estudiante();
				estudiante.setNombres(rs.getString("Nombres"));
				estudiante.setApellidos(rs.getString("Apellidos"));
				asistenciaEstudiante.setEstudiante(estudiante);

				// Inicializar y asignar datos a Curso
				Curso curso = new Curso();
				curso.setNombreCurso(rs.getString("NombreCurso"));
				asistenciaEstudiante.setCurso(curso);

				// Inicializar y asignar datos a Horario
				Horario horario = new Horario();
				horario.setDiaSemana(rs.getString("DiaSemana"));
				horario.setHoraInicioFin(rs.getString("HoraInicioFin"));
				asistenciaEstudiante.setHorario(horario);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return asistenciaEstudiante;
	}

	@Override
	public int eliminar(int asistenciaID) {
		int resultado = 0;
		try {
			conexion = MySqlConexion.getConexion();
			String sql = "DELETE FROM ASISTENCIA_ESTUDIANTE WHERE AsistenciaID = ?";
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setInt(1, asistenciaID);

			resultado = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	private String mensajeError = "";

	@Override
	public int actualizar(AsistenciaEstudiante asistenciaEstudiante) {

		int resultado = 0;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = MySqlConexion.getConexion();
			String sql = "UPDATE asistencia_estudiante SET EstadoAsistencia=?, Comentario=?, FECHADECLASE=?, FechaActualizacion=? WHERE AsistenciaID=?";
			pst = conn.prepareStatement(sql);

			// Asignar valores a los parámetros
			pst.setString(1, asistenciaEstudiante.getEstadoAsistencia());
			pst.setString(2, asistenciaEstudiante.getComentario());
			pst.setDate(3, asistenciaEstudiante.getFechaDeClase()); // Asegurar que sea un java.sql.Date
			pst.setDate(4, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
			pst.setInt(5, asistenciaEstudiante.getAsistenciaID());

			// Ejecutar la actualización
			resultado = pst.executeUpdate();

		} catch (SQLException e) {
			mensajeError = " " + e.getMessage();
			System.err.println("Falla al actualizar la asistencia: " + e.getMessage());
			e.printStackTrace(); // Puedes reemplazarlo por un logger
		} finally {
			// Cerrar recursos en orden inverso de apertura
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				mensajeError = "Error : " + e.getMessage();
				System.err.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return resultado;
	}

	public int registrar(AsistenciaEstudiante asistenciaEstudiante) {
		// TODO Auto-generated method stub
		int resultado = 0;

		PreparedStatement pst = null;

		try {
			conexion = MySqlConexion.getConexion();
			String sql = "insert into asistencia_estudiante (AsistenciaID,EstudianteID, HorarioID, EstadoAsistencia, Comentario, FECHADECLASE, UsuarioRegistro)"
					+ "VALUES (null, ?, ?, ?, ?, ?, ?)";

			pst = conexion.prepareStatement(sql);

			// Asignar valores a los parámetros
			pst.setInt(1, asistenciaEstudiante.getEstudianteID());
			pst.setInt(2, asistenciaEstudiante.getHorarioID());
			pst.setString(3, asistenciaEstudiante.getEstadoAsistencia()); // Asegurar que sea un java.sql.Date
			pst.setString(4, asistenciaEstudiante.getComentario()); // Fecha actual
			pst.setDate(5, asistenciaEstudiante.getFechaDeClase());
			pst.setString(6, asistenciaEstudiante.getUsuarioRegistro());
			// Ejecutar la actualización
			resultado = pst.executeUpdate();

		} catch (SQLException e) {

			mensajeError = " " + e.getMessage();
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace(); // Puedes reemplazarlo por un logger
		} finally {
			// Cerrar recursos en orden inverso de apertura
			try {
				if (pst != null)
					pst.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				mensajeError = "Error : " + e.getMessage();
				System.err.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return resultado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	@Override
	public int generarAutomatico(int horarioid, int estudianteid) {
		int resultado = 0;
		@SuppressWarnings("unused")
		ResultSet rs = null;
		PreparedStatement psm = null;

		try {
			conexion = MySqlConexion.getConexion();
			String sql = " CALL GenerarAsistenciaAutomatica(?, ?)";
			psm = conexion.prepareStatement(sql);

			psm.setInt(1, horarioid);
			psm.setInt(2, estudianteid);
			rs = psm.executeQuery();

			resultado = psm.executeUpdate();
		} catch (SQLException e) {
			mensajeError = "Error: " + e.getMessage();
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace(); // Puedes reemplazarlo por un logger
		} finally {
			// Cerrar recursos en orden inverso de apertura
			try {
				if (psm != null)
					psm.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				mensajeError = "Error : " + e.getMessage();
				System.err.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return resultado;
	}

	@Override
	public int aprobar(int asistenciaID) {
		int resultado = 0;
		try {
			conexion = MySqlConexion.getConexion();
			String sql = "UPDATE asistencia_estudiante SET EstadoAsistencia = ? WHERE AsistenciaID = ?";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setString(1, "Asistencia");
			pst.setInt(2, asistenciaID);

			resultado = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public int rechazar(int asistenciaID) {
		int resultado = 0;
		try {
			conexion = MySqlConexion.getConexion();
			String sql = "UPDATE asistencia_estudiante SET EstadoAsistencia = ? WHERE AsistenciaID = ?";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setString(1, "Inasistencia");
			pst.setInt(2, asistenciaID);

			resultado = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}
