<%@page import="modelo.EstudianteModel"%>
<%@page import="modelo.HorarioModel"%>
<%@page import="entidades.Estudiante"%>
<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>Registrar Matrícula</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="../index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Registrar Matrícula</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">

		<div class="row">

			<form action="../MatriculaServlet?tipo=regist" method="post">
				<div class="form-group">
					<label>Código de Matrícula</label> 
					<input class="form-control" type="text" name="txtCodigoMatricula" required>
				</div>
				
				<div class="form-group">
					<label>Estudiante</label> 
					<select id="txtIdEstudiante" name="txtIdEstudiante" class="form-control" required>
						<option value="">Selecciona un Estudiante</option>
						<%
						EstudianteModel estudianteModel = new EstudianteModel();
						List<Estudiante> listaEstudiantes = estudianteModel.listEstudiante();
						for (Estudiante estudiante : listaEstudiantes) {
						%>
						<option value="<%=estudiante.getEstudianteID()%>">
							<%=estudiante.getNombres() + " " + estudiante.getApellidos()%>
						</option>
						<%
						}
						%>
					</select>
				</div>

				<div class="form-group">
					<label>Horario</label> 
					<!-- SELECT DINÁMICO DE HORARIOS -->
					<select id="txtIdHorario" name="txtIdHorario" class="form-control" required>
					    <option value="">Selecciona un Horario</option>
					    
					    <%
				    	HorarioModel model = new HorarioModel();
				        List<Horario> listaHorarios = model.listHorario();
				        for (Horario item : listaHorarios) {
					    %>
		                <option value="<%=item.getHorarioID()%>">
		                 	<%="Curso: " + item.getNombreCurso()%> -
						<%="Profesor: " + item.getNombreProfesor()%>
						<%=item.getApellidoProfesor()%> -
						<%=item.getDiaSemana()%> -
						<%=item.getHoraInicioFin()%>
		                </option>
					    <%
					    }
					    %>
					</select>
				</div>

				
				<div class="form-group">
				    <label>Estado de Matrícula</label>
				    <select id="txtEstadoMatricula" name="txtEstadoMatricula" class="form-control" required>
				        <option value="">Selecciona un Estado</option>
				        <option value="Activo">Activo</option>
				        <option value="Inactivo">Inactivo</option>
				    </select>
				</div>

				<div class="form-group">
					<label>Observaciones</label> 
					<input class="form-control" type="text" name="txtObservaciones" required> 
				</div>

				<div class="form-group">
					<label>Modo de Matrícula</label> 
					<input class="form-control" type="text" name="txtModoMatricula" required>
				</div>


					<div class="form-group">
				    <label>Ciclo</label>
				    <select id="txtCiclo" name="txtCiclo" class="form-control" required>
				        <option value="">Selecciona un Ciclo</option>
				        <option value="Primer Ciclo">Primer Ciclo</option>
				        <option value="Segundo Ciclo">Segundo Ciclo</option>
				        <option value="Tercer Ciclo">Tercer Ciclo</option>
				        <option value="Cuarto Ciclo">Cuarto Ciclo</option>
				        <option value="Quinto Ciclo">Quinto Ciclo</option>
				        <option value="Sexto Ciclo">Sexto Ciclo</option>
				    </select>
				</div>
				
				
				
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" >
					<a href="../MatriculaServlet?tipo=list" role="button" class="btn btn-secondary">Listar</a>
				</div>
				
				<%-- Mostrar mensaje de error o éxito --%>
<%
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null && !mensaje.isEmpty()) {
%>
    <div style="color: red; font-weight: bold;">
        <%= mensaje %>
    </div>
<%
    }
%>
				
				
			</form>
		</div>
	</section>

</main>

<%@ include file="../footer.jsp"%>
