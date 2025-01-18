<%@page import="entidades.Asistencia"%>
<%@page import="entidades.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Lista de Asistencia</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<form method="post" action="AsistenciaServlet?tipo=list">
				<div class="row mb-3">
					<div class="col-md-3">
						<label for="fechaInicio">Fecha de Inicio:</label> <input
							type="date" id="fechaInicio" name="fechaInicio"
							class="form-control"
							value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>">
					</div>
					<div class="col-md-3">
						<label for="fechaFin">Fecha de Fin:</label> <input type="date"
							id="fechaFin" name="fechaFin" class="form-control"
							value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>">
					</div>
					<div class="col-md-3">
						<label for="cursoID">Curso:</label> <select id="cursoID"
							name="cursoID" class="form-control">
							<option value="">Selecciona un Curso</option>
							<%
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
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary w-100 mt-4">Buscar</button>
					</div>
				</div>
			</form>

			<hr>

			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Estudiante</th>
						<th>Fecha</th>
						<th>Curso</th>
						<th>Tipo Asistencia</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
            List<Asistencia> listaAsistencia = (List<Asistencia>) request.getAttribute("lista");
            if (listaAsistencia != null) {
                for (Asistencia item : listaAsistencia) {
          %>
					<tr>
						<td><%= item.getAsistenciaID() %></td>
						<td><%= item.getEstudiante().getNombres() + " " + item.getEstudiante().getApellidos() %></td>
						<td><%= item.getFechaRegistro() %></td>
						<td><%= item.getCurso().getNombreCurso() %></td>
						<td><%= item.getTipoAsistencia() %></td>
						<td><%= item.getEstado() %></td>
						<td><a
							href="AsistenciaServlet?tipo=delete&id=<%=item.getAsistenciaID()%>">
								<img alt="" src="image/ic_delete.svg" width="15" height="15"
								title="Eliminar">
						</a> <!--  <a id="deleteAsistencia" onclick="Eliminar('<%= item.getAsistenciaID() %>')" >
                  <img alt="" src="image/ic_delete.svg" width="15" height="15" title="Eliminar">
                </a>     --></td>
					</tr>
					<%
                }
            }
          %>
				</tbody>
			</table>
		</div>

	
	</section>
</main>

<%@ include file="../footer.jsp"%>
