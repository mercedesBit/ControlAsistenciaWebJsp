package servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Estudiante;
import entidades.Horario;
import entidades.Matricula;
import modelo.EstudianteModel;
import modelo.MatriculaModel;

@WebServlet("/MatriculaServlet")
public class MatriculaServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MatriculaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        switch(tipo) {
        case "list" : listMatricula(request, response); break;
        case "regist" : try {
				registMatricula(request, response);
			} catch (SQLIntegrityConstraintViolationException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
        }    
    }

    protected void listMatricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	MatriculaModel model = new MatriculaModel();
        
        List<Matricula> lista = model.listMatricula();
        
        request.setAttribute("listaMatricula", lista);
        request.getRequestDispatcher("matricula/listMatricula.jsp").forward(request, response);
    }

    protected void registMatricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, java.sql.SQLIntegrityConstraintViolationException {
        String codigoMatricula = request.getParameter("txtCodigoMatricula");
        
        // Manejo seguro de valores enteros
        int idEstudiante = 0;
        int idHorario = 0;
        
        try {
            idEstudiante = Integer.parseInt(request.getParameter("txtIdEstudiante"));
            idHorario = Integer.parseInt(request.getParameter("txtIdHorario"));
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Error: ID de estudiante o ID de horario inválido.");
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
            return;
        }

        String estadoMatricula = request.getParameter("txtEstadoMatricula");
        String observaciones = request.getParameter("txtObservaciones");
        String modoMatricula = request.getParameter("txtModoMatricula");
        String ciclo = request.getParameter("txtCiclo");

        // Imprimir valores obtenidos
        System.out.println("Código de Matrícula: " + codigoMatricula);
        System.out.println("ID Estudiante: " + idEstudiante);
        System.out.println("ID Horario: " + idHorario);
        System.out.println("Estado de Matrícula: " + estadoMatricula);
        System.out.println("Observaciones: " + observaciones);
        System.out.println("Modo de Matrícula: " + modoMatricula);
        System.out.println("Ciclo: " + ciclo);

        Matricula matricula = new Matricula();
        matricula.setCodigoMatricula(codigoMatricula);

        EstudianteModel estudianteModel = new EstudianteModel();
        Estudiante estudiante = estudianteModel.obtenerEstudiante(idEstudiante);
        matricula.setEstudiante(estudiante);

        Horario horario = new Horario();
        horario.setHorarioID(idHorario);
        matricula.setHorario(horario);

        matricula.setFechaMatricula(new java.sql.Date(System.currentTimeMillis()));
        matricula.setEstadoMatricula(estadoMatricula);
        matricula.setObservaciones(observaciones);
        matricula.setModoMatricula(modoMatricula);
        matricula.setCiclo(ciclo);

        MatriculaModel matriculaModel = new MatriculaModel();
        
        try {
            int value = matriculaModel.registrarMatricula(matricula);
            
            if (value == 1) {
                listMatricula(request, response);
            } else {
                request.setAttribute("mensaje", "Matrícula ya existe");
                request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
        }
    }


}
