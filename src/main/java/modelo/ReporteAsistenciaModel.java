package modelo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import util.MySqlConexion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

public class ReporteAsistenciaModel {

    private static String horarioID;
    private static String estudianteID;

    public static String getHorarioID() {
        return horarioID;
    }

    public static void setHorarioID(String horarioID) {
        ReporteAsistenciaModel.horarioID = horarioID;
    }

    public static String getEstudianteID() {
        return estudianteID;
    }

    public static void setEstudianteID(String estudianteID) {
        ReporteAsistenciaModel.estudianteID = estudianteID;
    }

    public static void generarReporte(String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Título del reporte
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Asistencia", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Espacio en blanco

            // Crear tabla
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            // Encabezados
            addTableHeader(table);

            // Datos de la tabla + información del estudiante y curso
            addRows(table, document);

            // Agregar la tabla al documento
            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of( "Fecha de Clase", "Día de Asistencia", "Comentario", 
                  "Estado de Asistencia", "Usuario de registro", "Fecha Actualización")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(new GrayColor(0.75f)); // Gris claro
                    header.setBorderWidth(1);
                    header.setPadding(5);
                    header.setPhrase(new Phrase(columnTitle, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, Document document) {
        Connection cn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            String idHorario = getHorarioID();
            String idEstudiante = getEstudianteID();

            cn = MySqlConexion.getConexion();
            String sql = "{ CALL ObtenerAsistenciaPorEstudianteYHorario(?, ?) }";
            psm = cn.prepareStatement(sql);
            psm.setString(1, idEstudiante);
            psm.setString(2, idHorario);
            rs = psm.executeQuery();

            boolean firstRow = true;

            while (rs.next()) {
                if (firstRow) {
                    // Extraer nombre del estudiante y curso de la primera fila
                	String id = rs.getString("EstudianteID");
                    String nombre = rs.getString("Nombres");
                    String apellido = rs.getString("Apellidos");
                    String tipoDoc = rs.getString("TipoDocumento");
                    String NumeroDocumento = rs.getString("NumeroDocumento");
                  String nombreCurso = rs.getString("NombreCurso");
                    String FechaInicio = rs.getString("FechaInicio");
                    String FechaFin = rs.getString("FechaFin");
                    String DiaSemana = rs.getString("DiaSemana");
                    String profesor = rs.getString("profesor");
                    String profesorape = rs.getString("profesorApe");
                    
                    // Agregar la información general antes de la tabla
                    document.add(new Paragraph("Estudiante: ID-"+id +"-"+ nombre + " " + apellido + "-" + " Documento: "+tipoDoc+ "-" + NumeroDocumento));
                    document.add(new Paragraph("Horario: " + DiaSemana + " - Curso: "+ nombreCurso+ "\n" 
                     +"Rango de Fechas: "+FechaInicio + " hasta " + FechaFin + " "+ "Profesor : " + profesor + " "+ profesorape ));
                    document.add(new Paragraph(" ")); 

                    firstRow = false;
                }

            
                addCell(table, String.valueOf(rs.getDate("FECHADECLASE")));
                addCell(table, rs.getString("DIAASISTENCIA"));
                addCell(table, rs.getString("Comentario"));
                addCell(table, rs.getString("EstadoAsistencia"));
                addCell(table, rs.getString("UsuarioRegistro"));
                addCell(table, rs.getString("FECHAACTUALIZACION") == null ?   "           -" : String.valueOf(rs.getDate("FECHAACTUALIZACION")));

            }
        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        cell.setBorderWidth(1);
        table.addCell(cell);
    }

    public static void main(String[] args) {
        setHorarioID("1"); // Simulación de ID de horario
        setEstudianteID("1001"); // Simulación de ID de estudiante
        generarReporte("asistencia-Alumno.pdf");
    }
}
