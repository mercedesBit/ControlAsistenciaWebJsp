package servlets;

import entidades.Curso;
import interfaces.Curso_DAO_interface;
import modelo.CursoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

@WebServlet("/CursoReporteServlet")
public class CursoServletDAO extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Curso_DAO_interface cursoDAO = new CursoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "reporte";
        }

        switch (action) {
            case "reporte":
                List<Curso> cursos = cursoDAO.obtenerTodosLosCursos();
                request.setAttribute("cursos", cursos);
                request.getRequestDispatcher("reporte/reporteCurso.jsp").forward(request, response);
                break;
            case "downloadPNG":
                generarImagenPNG(response);
                break;
            default:
                response.sendRedirect("reporte/reporteCurso.jsp");
                break;
        }
    }

    private void generarImagenPNG(HttpServletResponse response) throws IOException {
        List<Curso> cursos = cursoDAO.obtenerTodosLosCursos();
        int width = 1000;
        int height = 300 + (cursos.size() * 30);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Configuración del fondo
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Configuración del texto
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Encabezados de la tabla
        String[] headers = {"ID", "Código", "Nombre", "Descripción", "Duración", "Grado", "Nivel", "Modalidad", "Fecha Inicio", "Fecha Fin"};
        int y = 50;
        for (int i = 0; i < headers.length; i++) {
            g2d.drawString(headers[i], i * 100 + 10, y);
        }

        // Datos de los cursos
        y += 30;
        for (Curso c : cursos) {
            g2d.drawString(String.valueOf(c.getCursoID()), 10, y);
            g2d.drawString(c.getCodigoCurso(), 110, y);
            g2d.drawString(c.getNombreCurso(), 210, y);
            g2d.drawString(c.getDescripcion(), 310, y);
            g2d.drawString(String.valueOf(c.getDuracion()), 410, y);
            g2d.drawString(c.getGrado(), 510, y);
            g2d.drawString(c.getNivel(), 610, y);
            g2d.drawString(c.getModalidad(), 710, y);
            g2d.drawString(c.getFechaInicio().toString(), 810, y);
            g2d.drawString(c.getFechaFin().toString(), 910, y);
            y += 30;
        }

        g2d.dispose();

        // Convertir la imagen a base64 y enviar como respuesta
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageData = baos.toByteArray();

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte.png\"");
        response.getOutputStream().write(imageData);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
