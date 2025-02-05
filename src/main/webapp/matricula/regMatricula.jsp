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
					<input class="form-control" type="text" name="txtCodigoMatricula">
				</div>
				
				<div class="form-group">
					<label>Estudiante</label> 
					<select id="txtIdEstudiante" name="txtIdEstudiante" class="form-control">
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
					<select id="txtIdHorario" name="txtIdHorario" class="form-control">
					    <option value="">Selecciona un Horario</option>
					    
					    <%
				    	HorarioModel model = new HorarioModel();
				        List<Horario> listaHorarios = model.listHorario();
				        for (Horario horario : listaHorarios) {
					    %>
		                <option value="<%=horario.getHorarioID()%>">
		                    <%=horario.getDiaSemana() %>
		                </option>
					    <%
					    }
					    %>
					</select>
				</div>

				
				<div class="form-group">
				    <label>Estado de Matrícula</label>
				    <select id="txtEstadoMatricula" name="txtEstadoMatricula" class="form-control">
				        <option value="">Selecciona un Estado</option>
				        <option value="Activo">Activo</option>
				        <option value="Inactivo">Inactivo</option>
				    </select>
				</div>

				<div class="form-group">
					<label>Observaciones</label> 
					<input class="form-control" type="text" name="txtObservaciones">
				</div>

				<div class="form-group">
					<label>Modo de Matrícula</label> 
					<input class="form-control" type="text" name="txtModoMatricula">
				</div>


				<div class="form-group">
					<label>Ciclo</label> 
					<input class="form-control" type="text" name="txtCiclo">
				</div>
				
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" value="Registrar">
					<a href="../MatriculaServlet?tipo=list" role="button" class="btn btn-secondary">Listar</a>
				</div>
			</form>
		</div>
	</section>

</main>

<%@ include file="../footer.jsp"%>
