package modelo;

import entidades.PersonalAdministrativo;
import util.MySqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalAdministrativoModel {

    // Método para listar todos los registros de personal administrativo
    public List<PersonalAdministrativo> listarTodos() {
        List<PersonalAdministrativo> lista = new ArrayList<>();
        String sql = "SELECT * FROM PersonalAdministrativo";
        
        try (Connection conn = MySqlConexion.getConexion();  // Usamos DatabaseUtil para obtener la conexión
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PersonalAdministrativo personal = new PersonalAdministrativo();
                personal.setPersonalID(rs.getInt("PersonalID"));
                personal.setTipoDocumento(rs.getString("TipoDocumento"));
                personal.setNumeroDocumento(rs.getString("NumeroDocumento"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCorreoElectronico(rs.getString("CorreoElectronico"));
                personal.setTelefonoMovil(rs.getString("TelefonoMovil"));
                personal.setTelefonoTrabajo(rs.getString("TelefonoTrabajo"));
                personal.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                personal.setDireccion(rs.getString("Direccion"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setFechaContratacion(rs.getDate("FechaContratacion"));
                personal.setEstado(rs.getString("Estado"));
                personal.setSueldo(rs.getBigDecimal("Sueldo"));
                personal.setRedesSociales(rs.getString("RedesSociales"));
                personal.setFechaRegistro(rs.getDate("FechaRegistro"));
                personal.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                personal.setFechaActualizacion(rs.getDate("FechaActualizacion"));

                lista.add(personal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para obtener un registro de personal administrativo por ID
    public PersonalAdministrativo obtenerPorID(int id) {
        PersonalAdministrativo personal = null;
        String sql = "SELECT * FROM PersonalAdministrativo WHERE PersonalID = ?";
        
        try (Connection conn = MySqlConexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                personal = new PersonalAdministrativo();
                personal.setPersonalID(rs.getInt("PersonalID"));
                personal.setTipoDocumento(rs.getString("TipoDocumento"));
                personal.setNumeroDocumento(rs.getString("NumeroDocumento"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCorreoElectronico(rs.getString("CorreoElectronico"));
                personal.setTelefonoMovil(rs.getString("TelefonoMovil"));
                personal.setTelefonoTrabajo(rs.getString("TelefonoTrabajo"));
                personal.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                personal.setDireccion(rs.getString("Direccion"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setFechaContratacion(rs.getDate("FechaContratacion"));
                personal.setEstado(rs.getString("Estado"));
                personal.setSueldo(rs.getBigDecimal("Sueldo"));
                personal.setRedesSociales(rs.getString("RedesSociales"));
                personal.setFechaRegistro(rs.getDate("FechaRegistro"));
                personal.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                personal.setFechaActualizacion(rs.getDate("FechaActualizacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return personal;
    }

    public void registrar(PersonalAdministrativo personal) {
        // Validación previa de los campos obligatorios
        if (personal.getNombres() == null || personal.getNombres().isEmpty() ||
            personal.getApellidos() == null || personal.getApellidos().isEmpty() ||
            personal.getTipoDocumento() == null || personal.getTipoDocumento().isEmpty() ||
            personal.getNumeroDocumento() == null || personal.getNumeroDocumento().isEmpty()) {
            System.out.println("Error: Campos obligatorios faltantes.");
            return; // Salir del método
        }

        // Verificar si el ID ya existe
        String sqlCheck = "SELECT COUNT(*) FROM PersonalAdministrativo WHERE PersonalID = ?";
        try (Connection conn = MySqlConexion.getConexion();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setInt(1, personal.getPersonalID());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: El PersonalID ya existe en la base de datos.");
                return; // Salir del método
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia del ID: " + e.getMessage());
            e.printStackTrace();
            return; // Salir del método en caso de error
        }

        // Preparar la consulta de inserción
        String sql = "INSERT INTO PersonalAdministrativo (PersonalID, TipoDocumento, NumeroDocumento, Nombres, Apellidos, CorreoElectronico, TelefonoMovil, TelefonoTrabajo, FechaNacimiento, Direccion, Cargo, FechaContratacion, Estado, Sueldo, RedesSociales, FechaRegistro, UsuarioRegistro, FechaActualizacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySqlConexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asignar valores a los parámetros
            stmt.setInt(1, personal.getPersonalID());
            stmt.setString(2, personal.getTipoDocumento());
            stmt.setString(3, personal.getNumeroDocumento());
            stmt.setString(4, personal.getNombres());
            stmt.setString(5, personal.getApellidos());
            stmt.setString(6, personal.getCorreoElectronico());
            stmt.setString(7, personal.getTelefonoMovil());
            stmt.setString(8, personal.getTelefonoTrabajo());
            stmt.setDate(9, personal.getFechaNacimiento() != null ? new java.sql.Date(personal.getFechaNacimiento().getTime()) : null);
            stmt.setString(10, personal.getDireccion());
            stmt.setString(11, personal.getCargo());
            stmt.setDate(12, personal.getFechaContratacion() != null ? new java.sql.Date(personal.getFechaContratacion().getTime()) : null);
            stmt.setString(13, personal.getEstado());
            stmt.setBigDecimal(14, personal.getSueldo());
            stmt.setString(15, personal.getRedesSociales());
            stmt.setDate(16, personal.getFechaRegistro() != null ? new java.sql.Date(personal.getFechaRegistro().getTime()) : null);
            stmt.setString(17, personal.getUsuarioRegistro());
            stmt.setDate(18, personal.getFechaActualizacion() != null ? new java.sql.Date(personal.getFechaActualizacion().getTime()) : null);

            // Ejecutar la consulta
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro exitoso.");
            } else {
                System.out.println("No se pudo registrar el personal.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el personal: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }


    // Método para actualizar un registro de personal administrativo
    public int actualizar(PersonalAdministrativo personal) {
        String sql = "UPDATE PersonalAdministrativo SET TipoDocumento = ?, NumeroDocumento = ?, Nombres = ?, Apellidos = ?, CorreoElectronico = ?, TelefonoMovil = ?, TelefonoTrabajo = ?, FechaNacimiento = ?, Direccion = ?, Cargo = ?, FechaContratacion = ?, Estado = ?, Sueldo = ?, RedesSociales = ?, FechaActualizacion = ? WHERE PersonalID = ?";
        int filasAfectadas = 0;
        
        try (Connection conn = MySqlConexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personal.getTipoDocumento());
            stmt.setString(2, personal.getNumeroDocumento());
            stmt.setString(3, personal.getNombres());
            stmt.setString(4, personal.getApellidos());
            stmt.setString(5, personal.getCorreoElectronico());
            stmt.setString(6, personal.getTelefonoMovil());
            stmt.setString(7, personal.getTelefonoTrabajo());
            stmt.setDate(8, new java.sql.Date(personal.getFechaNacimiento().getTime()));
            stmt.setString(9, personal.getDireccion());
            stmt.setString(10, personal.getCargo());
            stmt.setDate(11, new java.sql.Date(personal.getFechaContratacion().getTime()));
            stmt.setString(12, personal.getEstado());
            stmt.setBigDecimal(13, personal.getSueldo());
            stmt.setString(14, personal.getRedesSociales());
            stmt.setDate(15, new java.sql.Date(personal.getFechaActualizacion().getTime()));
            stmt.setInt(16, personal.getPersonalID());
            
            filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return filasAfectadas;
    }

    // Método para eliminar un registro de personal administrativo
    public int eliminar(int id) {
        String sql = "DELETE FROM PersonalAdministrativo WHERE PersonalID = ?";
        int filasAfectadas = 0;
        
        try (Connection conn = MySqlConexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return filasAfectadas;
    }
    public List<PersonalAdministrativo> buscarPorFiltro(String filtro) {
        List<PersonalAdministrativo> lista = new ArrayList<>();
        try {
            Connection conn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM PersonalAdministrativo WHERE Nombres LIKE ? OR Apellidos LIKE ? OR Cargo LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            ps.setString(3, "%" + filtro + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PersonalAdministrativo personal = new PersonalAdministrativo();
                personal.setPersonalID(rs.getInt("PersonalID"));
                personal.setTipoDocumento(rs.getString("TipoDocumento"));
                personal.setNumeroDocumento(rs.getString("NumeroDocumento"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCorreoElectronico(rs.getString("CorreoElectronico"));
                personal.setTelefonoMovil(rs.getString("TelefonoMovil"));
                personal.setTelefonoTrabajo(rs.getString("TelefonoTrabajo"));
                personal.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                personal.setDireccion(rs.getString("Direccion"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setFechaContratacion(rs.getDate("FechaContratacion"));
                personal.setEstado(rs.getString("Estado"));
                personal.setSueldo(rs.getBigDecimal("Sueldo"));
                personal.setRedesSociales(rs.getString("RedesSociales"));
                personal.setFechaRegistro(rs.getDate("FechaRegistro"));
                personal.setUsuarioRegistro(rs.getString("UsuarioRegistro"));
                personal.setFechaActualizacion(rs.getDate("FechaActualizacion"));

                lista.add(personal);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<PersonalAdministrativo> buscarAvanzado(String filtro, String cargo, String fechaInicio, String fechaFin) {
        List<PersonalAdministrativo> lista = new ArrayList<>();
        try {
            Connection conn = MySqlConexion.getConexion();
            StringBuilder sql = new StringBuilder("SELECT * FROM PersonalAdministrativo WHERE 1=1");
            
            if (filtro != null && !filtro.isEmpty()) {
                sql.append(" AND (Nombres LIKE ? OR Apellidos LIKE ?)");
            }
            if (cargo != null && !cargo.isEmpty()) {
                sql.append(" AND Cargo = ?");
            }
            if (fechaInicio != null && !fechaInicio.isEmpty()) {
                sql.append(" AND FechaContratacion >= ?");
            }
            if (fechaFin != null && !fechaFin.isEmpty()) {
                sql.append(" AND FechaContratacion <= ?");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (filtro != null && !filtro.isEmpty()) {
                ps.setString(paramIndex++, "%" + filtro + "%");
                ps.setString(paramIndex++, "%" + filtro + "%");
            }
            if (cargo != null && !cargo.isEmpty()) {
                ps.setString(paramIndex++, cargo);
            }
            if (fechaInicio != null && !fechaInicio.isEmpty()) {
                ps.setString(paramIndex++, fechaInicio);
            }
            if (fechaFin != null && !fechaFin.isEmpty()) {
                ps.setString(paramIndex++, fechaFin);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PersonalAdministrativo personal = new PersonalAdministrativo();
                personal.setPersonalID(rs.getInt("PersonalID"));
                personal.setNombres(rs.getString("Nombres"));
                personal.setApellidos(rs.getString("Apellidos"));
                personal.setCargo(rs.getString("Cargo"));
                personal.setFechaContratacion(rs.getDate("FechaContratacion"));
                lista.add(personal);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

        public List<String> obtenerCargos() {
            List<String> cargos = new ArrayList<>();
            try {
                Connection conn = MySqlConexion.getConexion(); 
                String sql = "SELECT DISTINCT Cargo FROM PersonalAdministrativo ORDER BY Cargo";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    cargos.add(rs.getString("Cargo"));
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cargos;
        }
}
