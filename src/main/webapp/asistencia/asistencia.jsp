<%@page import="entidades.HorarioEstudiante"%>
<%@page import="entidades.Estudiante"%>
<%@page import="entidades.Horario"%>
<%@page import="entidades.AsistenciaEstudiante"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Lista de Asistencia</title>
</head>
<body>



	<form id="horarioForm" action="AsistenciaEstudianteServlet" method="post">
    <label for="horarioID">Seleccionar Horario:</label> 
<select name="horarioID" id="horarioID" onchange="this.form.submit()">
    <option value="">Seleccione un horario</option>
    <%
    @SuppressWarnings("unchecked")
    List<Horario> listaHorarios = (List<Horario>) request.getAttribute("listaHorario");
    String horarioSeleccionado = request.getParameter("horarioID"); // Obtiene el horario seleccionado

    if (listaHorarios != null && !listaHorarios.isEmpty()) {
        for (Horario item : listaHorarios) {
    %>
        <option value="<%= item.getHorarioID() %>" 
            <%= (horarioSeleccionado != null && horarioSeleccionado.equals(String.valueOf(item.getHorarioID()))) ? "selected" : "" %>>
            <%= item.getNombreCurso() %> - <%= item.getNombreProfesor() %> <%= item.getApellidoProfesor() %> - <%= item.getDiaSemana() %> - <%= item.getHoraInicioFin() %>
        </option>
    <%
        }
    } else {
    %>
    <option value="">No hay horarios disponibles</option>
    <%
    }
    %>
</select>

    
    
    
    
    <label for="estudianteID">Seleccionar Estudiante:</label>
<select name="estudianteID" id="estudianteID">
    <%
        List<HorarioEstudiante> listaHorarioEstudiante = (List<HorarioEstudiante>) request.getAttribute("listaHorarioEstudiante");

        if (listaHorarioEstudiante != null && !listaHorarioEstudiante.isEmpty()) {
            for (HorarioEstudiante estudiante : listaHorarioEstudiante) {
    %>
                <option value="<%= estudiante.getHorarioID() %>">
                    <%= estudiante.getNombre() %> <%= estudiante.getApellido() %>
                </option>
    <%
            }
        } else {
    %>
        <option value="">Seleccione un horario para ver los estudiantes</option>
    <%
        }
    %>
</select>

    
</form>


	
	
	
	
	
	<script>
    document.getElementById("horarioID").addEventListener("change", function() {
        console.log("Horario seleccionado: " + this.value);
    });
</script>
	
	



</body>
</html>
