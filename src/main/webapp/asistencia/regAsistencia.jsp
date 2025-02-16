<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

	
	
<%@page import="java.util.Date"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>
<%@page import="entidades.AsistenciaEstudiante"%>
<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registrar Asistencia</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<%
			Horario horario = (Horario) request.getAttribute("horarioData");
			%>
			<div class="form-group">
				<label class="text-secondary">Horario</label> <input
					class="form-control" type="text"
					value="<%=(horario != null) ? horario.getDiaSemana() : ""%>"
					readonly>
					<br>
					 <input
					class="form-control" type="text"
					value="<%=(horario != null) ? horario.getHoraInicioFin() : ""%>"
					readonly>
					<br>
					   <input class="form-control" type="text"
        value="<%=(horario != null && horario.getFechaInicio() != null && horario.getFechaFin() != null) 
                ? "De " + horario.getFechaInicio().toString() + "hasta " + horario.getFechaFin().toString() 
                : ""%>" 
        readonly>
			</div>

			


		</div>


		<form action="AsistenciaEstudianteServlet" method="post" accept-charset="UTF-8">
			<input type="hidden" name="tipo" value="registrar"> <input
				type="hidden" id="horarioID" name="horarioID"
				value="<%=request.getAttribute("horarioID")%>" readonly> <input
				type="hidden" id="estudianteID" name="estudianteID"
				value="<%=request.getAttribute("EstudianteID")%>" readonly>


<br>
		


			<div class="col-md-6 mb-3">
				<label for="estado" class="form-label">Estado de Asistencia
				</label> <select class="form-control" id="estado" name="estado" required>
					<option value="Asistencia">Asistencia</option>
					<option value="Inasistencia">Inasistencia</option>

					<option value="En espera">En espera</option>
					<option value="Inasistencia Justificada">Inasistencia
						Justificada</option>
				</select>
			</div>



			<div class="form-group">
				<label for="nombreCurso">Comentario</label> <input type="text"
					class="form-control" id="comentario" name="comentario" required>
			</div>

<div class="form-group">
    <label for="fecha">Fecha de la Clase</label>
    <input 
        type="date" 
        class="form-control" 
        id="fecha" 
        name="fecha" 
        required>
</div>

			<br />
			<div class="form-actions">
				<button type="submit" class="btn btn-danger">Registrar</button>
				
				<%
    int horarioID = (request.getAttribute("horarioID") != null) ? (int) request.getAttribute("horarioID") : 0;
    int estudianteID = (request.getAttribute("EstudianteID") != null) ? (int) request.getAttribute("EstudianteID") : 0;
%>

<a href="AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=<%= horarioID %>&estudianteID=<%= estudianteID %>" 
  
   class="btn btn-secondary">Cancelar</a>
   
   <a href="AsistenciaEstudianteServlet?tipo=generarAutomatico&horarioID=<%= horarioID %>&estudianteID=<%= estudianteID %>" 
  
   class="btn btn-danger">Generar Asistencias Automáticas</a>
				
			
			</div>
		</form>
		<%
		String mensaje = (String) session.getAttribute("mensaje");
		%>
		<%
		if (mensaje != null) {
		%>
		<div class="alert alert-success">
			<%=mensaje%>
		</div>
		<%
		session.removeAttribute("mensaje");
		%>
		<!-- Eliminar para que no se repita -->
		<%
		}
		%>
		</div>
	</section>
</main>

<script>
    document.getElementById("fecha").valueAsDate = new Date();
</script>

<%@ include file="../footer.jsp"%>