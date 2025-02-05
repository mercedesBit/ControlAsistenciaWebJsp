<%@page import="entidades.Asistencia"%>
<%@page import="entidades.Estudiante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registro de Asistencia</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
		<h3>Asistencia</h3>
		<br>
		<form method="post" action="AsistenciaServlet?tipo=regist">

			<!-- Formulario de búsqueda -->
			<div class="row mb-3">

				<div class="col-md-3">
					<label for="cursoID">Curso:</label> <select id="cursoID"
						name="cursoID" class="form-control">
						<option value="">Selecciona un Curso</option>
						<%
						@SuppressWarnings("unchecked")
                List<Curso> listaCursos = (List<Curso>) request.getAttribute("listaCursos");
                for (Curso curso : listaCursos) {
              %>
						<option value="<%= curso.getCursoID() %>">
							<%= curso.getNombreCurso() %>
						</option>
						<%
                }
              %>
					</select>
				</div>

			</div>

			<hr>

			<!-- Tabla de resultados de asistencia -->
			<table class="table datatable">
				<thead>
					<tr>
						<th>Estudiante</th>
						<th>Tipo Asistencia</th>
					</tr>
				</thead>
				<tbody>
					<%
					@SuppressWarnings("unchecked")
                List<Estudiante> listaEstudiante = (List<Estudiante>) request.getAttribute("listaEstudiante");
                if (listaEstudiante != null) {
                    for (Estudiante item : listaEstudiante) {
            %>
					<tr>
						<td><%= item.getNombres() + " "+ item.getApellidos() %></td>
						<td><select
							name="tipoAsistencia_<%= item.getEstudianteID() %>"
							class="form-control">
								<option value="Presente">Presente</option>
								<option value="Ausente">Ausente</option>
								<option value="Ausente Justificado">Ausente Justificado</option>
								<option value="Llego Tarde">Llegó Tarde</option>
								<option value="Evadio">Evadió</option>
						</select></td>
					</tr>
					<%
                    }
                }
            %>
				</tbody>
			</table>
			<br> <input type="submit" class="btn btn-danger"
				value="Registrar">
		</form>

	</div>
</main>

<%@ include file="../footer.jsp"%>