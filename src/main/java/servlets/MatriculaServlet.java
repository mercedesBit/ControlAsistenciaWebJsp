package servlets;

import java.io.IOException;
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
        case "regist" : registMatricula(request, response); break;
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

    protected void registMatricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String codigoMatricula = request.getParameter("txtCodigoMatricula");
        int idEstudiante = Integer.parseInt(request.getParameter("txtIdEstudiante"));
        int idHorario = Integer.parseInt(request.getParameter("txtIdHorario"));
        String estadoMatricula = request.getParameter("txtEstadoMatricula");
        String observaciones = request.getParameter("txtObservaciones");
        String modoMatricula = request.getParameter("txtModoMatricula");
        String ciclo = request.getParameter("txtCiclo");
        
        Matricula matricula = new Matricula();
        
        matricula.setCodigoMatricula(codigoMatricula);
        
        EstudianteModel estudianteModel = new EstudianteModel();
        Estudiante estudiante = estudianteModel.obtenerEstudiante(idEstudiante);
        matricula.setEstudiante(estudiante);
        
   //     HorarioModel horarioModel = new HorarioModel();
     //   Horario horario = horarioModel.obtenerHorarioPorId(idHorario);
        Horario horario = new Horario();
        horario.setHorarioID(idHorario);
        matricula.setHorario(horario);
        
        matricula.setFechaMatricula( new java.sql.Date(System.currentTimeMillis()));
        matricula.setEstadoMatricula(estadoMatricula);
        matricula.setObservaciones(observaciones);
        matricula.setModoMatricula(modoMatricula);
        matricula.setCiclo(ciclo);
        
        MatriculaModel matriculaModel = new MatriculaModel();
        int value = matriculaModel.registrarMatricula(matricula);
        
        if (value == 1) {
            listMatricula(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrió un problema al registrar la matrícula.");
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
        }
    }

}
