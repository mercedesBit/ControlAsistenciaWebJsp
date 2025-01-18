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

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import util.MySqlConexion;

public class ReportePadresTutores {

    public static void generarReporte(String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Título del reporte
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Padres/Tutores", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Espacio en blanco

            // Tabla de datos
            PdfPTable table = new PdfPTable(8); // Número de columnas
            table.setWidthPercentage(100);

            // Encabezados de la tabla
            addTableHeader(table);

            // Datos de la tabla
            addRows(table);

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("ID", "tipoD", "numeroD", "Nombres", "Apellidos", "Correo", "TeléfonoM", "Dirección")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(new GrayColor(0.75f)); // Gris claro usando GrayColor
                    header.setBorderWidth(1);
                    header.setPadding(5);
                    header.setPhrase(new Phrase(columnTitle, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        Connection cn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT TutorID, TipoDocumento, NumeroDocumento, Nombres, Apellidos, CorreoElectronico, TelefonoMovil, Direccion FROM PadresTutores";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();

            while (rs.next()) {
                addCell(table, String.valueOf(rs.getInt("TutorID")));
                addCell(table, rs.getString("TipoDocumento"));
                addCell(table, rs.getString("NumeroDocumento"));
                addCell(table, rs.getString("Nombres"));
                addCell(table, rs.getString("Apellidos"));
                addCell(table, rs.getString("CorreoElectronico"));
                addCell(table, rs.getString("TelefonoMovil"));
                addCell(table, rs.getString("Direccion"));
            }
        } catch (SQLException e) {
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
        generarReporte("reporte_padres_tutores.pdf");
    }
}
