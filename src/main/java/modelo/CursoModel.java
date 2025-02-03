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
			String sql = "INSERT INTO Curso (CursoID, CodigoCurso, NombreCurso, Descripcion, Duracion, Ciclo, Nivel, Estado, Notas, FechaRegistro, UsuarioRegistro) "
					+ "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, curso.getCodigoCurso());
			pst.setString(2, curso.getNombreCurso());
			pst.setString(3, curso.getDescripcion());
			pst.setInt(4, curso.getDuracion());
			pst.setString(5, curso.getCiclo());
			pst.setString(6, curso.getNivel());
			pst.setString(7, curso.getEstado());
			pst.setString(8, curso.getNotas());
			pst.setDate(9, new java.sql.Date(curso.getFechaRegistro().getTime()));
			pst.setString(10, curso.getUsuarioRegistro());
		/*
			// pst.setDate(7, new java.sql.Date(curso.getFechaInicio().getTime()));
			// pst.setDate(8, new java.sql.Date(curso.getFechaFin().getTime()));
			pst.setString(8, curso.getRequisitosPrevios());
			pst.setInt(9, curso.getCantidadMaximaEstudiantes());
			pst.setString(10, curso.getModalidad());
			// pst.setInt(13, curso.getSeccionID());
			// pst.setString(14, curso.getTemario());
			// pst.setString(15, curso.getHorario());
			pst.setString(8, curso.getNotas());
			// pst.setInt(17, curso.getProfesorID());*/
		

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
			String sql = "UPDATE Curso SET CodigoCurso=?, NombreCurso=?, Descripcion=?,Duracion=?,  Ciclo=?, Nivel=?, Estado=?, Notas=?, FechaActualizacion=? WHERE CursoID=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, curso.getCodigoCurso());
			pst.setString(2, curso.getNombreCurso());
			pst.setString(3, curso.getDescripcion());
			pst.setInt(4, curso.getDuracion());
			pst.setString(5, curso.getCiclo());
			pst.setString(6, curso.getNivel());
			pst.setString(7, curso.getEstado());
			pst.setString(8, curso.getNotas());
			pst.setDate(9, new java.sql.Date(curso.getFechaActualizacion().getTime()));	
			pst.setInt(10, curso.getCursoID());
			/*
			 * 		// pst.setDate(7, new java.sql.Date(curso.getFechaInicio().getTime()));
			// pst.setDate(8, new java.sql.Date(curso.getFechaFin().getTime()));
			pst.setString(8, curso.getRequisitosPrevios());
			pst.setInt(9, curso.getCantidadMaximaEstudiantes());
			pst.setString(10, curso.getModalidad());
			// pst.setInt(13, curso.getSeccionID());
			// pst.setString(14, curso.getTemario());
			// pst.setString(15, curso.getHorario());
			 			// pst.setInt(17, curso.getProfesorID());
			 // pst.setDate(12, new java.sql.Date(System.currentTimeMillis()));
			 * */
			 
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
				
				
				//Metodo que obtiene el string dependiendo el Id que se obtiene 
				curso.setCiclo(obtenerNombreCicloPorID(rs.getInt("Ciclo")));
				//hOLA
				
				curso.setNivel(rs.getString("Nivel"));
				curso.setEstado(rs.getString("Estado"));
				curso.setNotas(rs.getString("Notas"));
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
				
				
				curso.setCiclo(rs.getString("Ciclo"));
				
				
				curso.setNivel(rs.getString("Nivel"));
				curso.setEstado(rs.getString("Estado"));
				curso.setNotas(rs.getString("Notas"));
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
	
	
	public List<Curso> obtenerTodosLosCiclos() {
	    List<Curso> ciclos = new ArrayList<>();
	    try {
	        conn = MySqlConexion.getConexion();
	        // Consulta SQL para obtener todos los ciclos
	        String sql = "SELECT * FROM Ciclo"; // Ajusta la consulta según la estructura de tu tabla
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        // Procesamos los resultados
	        while (rs.next()) {
	        	Curso ciclo = new Curso();
	            ciclo.setId_ciclo(rs.getInt("id_ciclo"));  
	            ciclo.setCiclo(rs.getString("nombre_ciclo")); 
	            ciclos.add(ciclo);
	        }
	        rs.close();
	        pst.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return ciclos;
	}
	
	
	
	public String obtenerNombreCicloPorID(int idCiclo) {
	    String cicloNombre = null;
	    try {
	        conn = MySqlConexion.getConexion();
	        String sql = "SELECT * FROM Ciclo WHERE id_ciclo = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setInt(1, idCiclo);  // Asumimos que el ID del ciclo es un entero

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            cicloNombre = rs.getString("nombre_ciclo"); // Aquí obtenemos el nombre del ciclo del atributo de BD
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
	    return cicloNombre;
	}


}
