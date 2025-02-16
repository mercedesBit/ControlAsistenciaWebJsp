<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%@page import="entidades.HorarioEstudiante"%>
<%@page import="entidades.Estudiante"%>
<%@page import="entidades.Horario"%>
<%@page import="entidades.AsistenciaEstudiante"%>
<%@ page import="java.util.*"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">


<main id="main" class="main">

	<div class="pagetitle">
		<h1>Registro de Asistencia por Alumno</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>


	<section class="section dashboard"></section>





	<div class="container mt-4">
		<form id="horarioForm" action="AsistenciaEstudianteServlet"
			method="get">
			<input type="hidden" name="tipo" value="listAsistenciaxAlumno">


			<div class="mb-3">

				<label for="horarioID" class="form-label">Seleccionar
					Horario:</label> <select class="form-select" name="horarioID"
					id="horarioID" onchange="this.form.submit()">
					<option value="">Seleccione un horario</option>
					<%
					@SuppressWarnings("unchecked")
					List<Horario> listaHorarios = (List<Horario>) request.getAttribute("listaHorario");
					String horarioSeleccionado = request.getParameter("horarioID"); // Obtiene el horario seleccionado

					if (listaHorarios != null && !listaHorarios.isEmpty()) {
						for (Horario item : listaHorarios) {
					%>
					<option value="<%=item.getHorarioID()%>"
						<%=(horarioSeleccionado != null && horarioSeleccionado.equals(String.valueOf(item.getHorarioID())))
		? "selected"
		: ""%>>
						<%="Curso: " + item.getNombreCurso()%> -
						<%="Profesor: " + item.getNombreProfesor()%>
						<%=item.getApellidoProfesor()%> -
						<%=item.getDiaSemana()%> -
						<%=item.getHoraInicioFin()%>
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
			</div>
			<br>


			<div class="mb-3">
				<label for="estudianteID" class="form-label">Seleccionar
					Estudiante:</label> <select class="form-select" name="estudianteID"
					id="estudianteID">
					<%
					List<HorarioEstudiante> listaHorarioEstudiante = (List<HorarioEstudiante>) request
							.getAttribute("listaHorarioEstudiante");
					String estudianteSeleccionado = request.getParameter("estudianteID");

					if (listaHorarioEstudiante != null && !listaHorarioEstudiante.isEmpty()) {
						for (HorarioEstudiante estudiante : listaHorarioEstudiante) {
					%>
					<option value="<%=estudiante.getEstudianteID()%>"
						<%=(estudianteSeleccionado != null && estudianteSeleccionado.equals(String.valueOf(estudiante.getEstudianteID())))
				? "selected"
				: ""%>>
						<%=estudiante.getNombre()%>
						<%=estudiante.getApellido()%>
					</option>
					<%
					}
					} else {
					%>
					<option value="">Seleccione un horario para ver los
						estudiantes</option>
					<%
					}
					%>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Buscar
				Asistencia</button>

		</form>

		<br>

	</div>
	<%
	String mensaje = (String) session.getAttribute("mensaje");
	%>
	<%
	if (mensaje != null) {
	%>
	<div class="alert alert-success alert-dismissible fade show"
		role="alert">
		<%=mensaje%>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
	<%
	session.removeAttribute("mensaje");
	%>
	<!-- Eliminar para que no se repita -->
	<%
	}
	%>



	<div class="row">
		<%
		List<AsistenciaEstudiante> listaAsistenciaEstudiante = (List<AsistenciaEstudiante>) request
				.getAttribute("listaAsistenciaEstudiante");
		String horarioID = request.getParameter("horarioID");
		String estudianteID = request.getParameter("estudianteID");

		if ((horarioID == null || horarioID.isEmpty()) || (estudianteID == null || estudianteID.isEmpty())) {
		%>
		<p>Seleccione el horario y el estudiante.</p>
		<%
		return; // Detiene la ejecución si los parámetros son nulos o vacíos
		} else if (listaAsistenciaEstudiante == null || listaAsistenciaEstudiante.isEmpty()) {
		%>
		<!-- Formulario que envía los datos al servlet -->
		<form action="AsistenciaEstudianteServlet?tipo=nuevo" method="POST">
			<input type="hidden" name="horarioID" value="<%=horarioID%>">
			<input type="hidden" name="estudianteID" value="<%=estudianteID%>">
			<button type="submit" class="btn btn-primary">Registrar
				Asistencia</button>
		</form>
		<p>No hay registros de asistencia para este estudiante en este
			horario.</p>
		<%
		} else {
		%>





		<%
		if (listaAsistenciaEstudiante != null && !listaAsistenciaEstudiante.isEmpty()) {
			AsistenciaEstudiante asistencia = listaAsistenciaEstudiante.get(0);
		%>


		<!-- Formulario que envía los datos al servlet -->
		<form action="AsistenciaEstudianteServlet?tipo=nuevo" method="POST">
			<input type="hidden" name="horarioID"
				value="<%=asistencia.getHorarioID()%>"> <input type="hidden"
				name="estudianteID" value="<%=asistencia.getEstudianteID()%>">
			<button type="submit" class="btn btn-primary">Registrar
				Asistencia</button>
		</form>

		<%
		} else {
		%>
		<p>No hay datos de asistencia disponibles.</p>

		<%
		}
		%>






		<table class="table datatable">
			<thead>
				<tr>
					<th>Fecha de Clase</th>
					<th>Día de Asistencia</th>
					<th>Comentario</th>
					<th>Estado</th>
					<th>Usuario de Registro</th>
					<th>Acciones</th>
					<th>Asistencia</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<%
					for (AsistenciaEstudiante asistencia : listaAsistenciaEstudiante) {
					%>



					<td><%=asistencia.getFechaDeClase()%></td>
					<td><%=asistencia.getDiaAsistencia()%></td>
					<td><%=asistencia.getComentario()%></td>
					<td><%=asistencia.getEstadoAsistencia()%></td>
					<td><%=asistencia.getUsuarioRegistro()%></td>
					<td><a
						href="AsistenciaEstudianteServlet?tipo=detalle&id=<%=asistencia.getAsistenciaID()%>">
							<img alt="" src="image/ic_info.svg" width="15" height="15"
							title="Detalle">
					</a> <a
						href="AsistenciaEstudianteServlet?tipo=editar&id=<%=asistencia.getAsistenciaID()%>">
							<img alt="" src="image/ic_edit.svg" width="15" height="15"
							title="Editar">
					</a> <a
						href="AsistenciaEstudianteServlet?tipo=eliminar&id=<%=asistencia.getAsistenciaID()%>"
						onclick="return confirm('¿Está seguro de eliminar este curso?')">
							<img alt="" src="image/ic_delete.svg" width="15" height="15"
							title="Eliminar">
					</a> 
					</td>
					
					<td>
					<a
						href="AsistenciaEstudianteServlet?tipo=aprobar&id=<%=asistencia.getAsistenciaID()%>">
							<img alt="Aprobar"
							src="https://cdn-icons-png.flaticon.com/512/845/845646.png"
							width="15" height="15" title="Aprobar">
					</a>  <a
						href="AsistenciaEstudianteServlet?tipo=rechazar&id=<%=asistencia.getAsistenciaID()%>">
							<img alt="Rechazar"
							src="https://cdn-icons-png.flaticon.com/512/1828/1828778.png"
							width="15" height="15" title="Rechazar">
					</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>








	</div>







	</section>

</main>


<%@ include file="../footer.jsp"%>



<script>

		
document.getElementById("horarioID").addEventListener("change", function() {
    const horarioID = this.value;
    document.getElementById("horarioSeleccionado").value = horarioID; // Actualizar el campo oculto
    
    fetch('AsistenciaEstudianteServlet?action=getEstudiantes&horarioID=' + horarioID)
        .then(response => response.json())
        .then(estudiantes => {
            const select = document.getElementById("estudianteID");
            select.innerHTML = '';
            estudiantes.forEach(estudiante => {
                const option = new Option(
                    estudiante.nombre + ' ' + estudiante.apellido, 
                    estudiante.estudianteID
                );
                select.add(option);
            });
        });
});

document.getElementById("estudianteID").addEventListener("change", function() {
    const estudianteID = this.value;
    document.getElementById("estudianteSeleccionado").value = estudianteID; // Actualizar el campo oculto
});


document.getElementById("asistenciaForm").addEventListener("submit", function(e) {
    const horarioID = document.getElementById("horarioSeleccionado").value;
    const estudianteID = document.getElementById("estudianteSeleccionado").value;
    
    if (!horarioID || !estudianteID) {
        e.preventDefault();
        alert("Por favor seleccione tanto el horario como el estudiante antes de registrar la asistencia.");
    }
});

		
	</script>



