package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import entidades.Estudiante;
import entidades.Horario;
import entidades.Matricula;
import entidades.PadresTutores;
import modelo.EstudianteModel;
import modelo.MatriculaModel;

@WebServlet("/MatriculaServlet")
public class MatriculaServlet {
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
        case "regist" : registEstudiante(request, response); break;
        default:
            request.setAttribute("mensaje", "Ocurrio un problema");
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
        }    
    }

    protected void listMatricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	MatriculaModel model = new MatriculaModel();
        
        List<Matricula> lista = model.listMatricula();
        
        request.setAttribute("listaMatriculas", lista);
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
        horario.setCodHorario(1);
        matricula.setHorario(horario);
        
        matricula.setFechaMatricula( new java.sql.Date(System.currentTimeMillis()));
        matricula.setEstadoMatricula(estadoMatricula);
        matricula.setObservaciones(observaciones);
        matricula.setModoMatricula(modoMatricula);
        matricula.setCiclo(ciclo);
        
        MatriculaModel matriculaModel = new MatriculaModel();
        int value = matriculaModel.registrarMatricula(matricula);
        
        if (value == 1) {
            listMatriculas(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrió un problema al registrar la matrícula.");
            request.getRequestDispatcher("matricula/regMatricula.jsp").forward(request, response);
        }
    }

}
