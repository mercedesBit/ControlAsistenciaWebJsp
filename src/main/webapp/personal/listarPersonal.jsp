<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="entidades.PersonalAdministrativo"%>
<%@page import="modelo.PersonalAdministrativoModel"%>
<%@page import="entidades.Curso"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.css">


<%@ page import="java.util.List"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Listar Personal Administrativo</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<a href="PersonalAdministrativoServlet?accion=nuevo"
			class="btn btn-primary">Crear Nuevo</a>
		<div class="row">
			<form class="search-form" method="get" action="listarPersonal.jsp">

				<div class="row mb-3">

					<!-- Formulario de búsqueda -->
					<div class="col-md-3">
						<label for="filtro">Nombres o Apellidos:</label> <input
							type="text" name="filtro" id="filtro" class="form-control"
							placeholder="Buscar por Nombres o Apellidos"
							value="<%=request.getParameter("filtro") != null ? request.getParameter("filtro") : ""%>">
					</div>
					<div class="col-md-3">
						<label for="cargo">Cargo:</label> <select name="cargo" id="cargo"
							class="form-control">
							<option value="">Todos</option>
							<%
							// Obtener la lista de cargos desde la base de datos
							PersonalAdministrativoModel model = new PersonalAdministrativoModel();
							List<String> cargos = model.obtenerCargos();
							for (String cargo : cargos) {
							%>
							<option value="<%=cargo%>"
								<%=request.getParameter("cargo") != null && request.getParameter("cargo").equals(cargo) ? "selected" : ""%>><%=cargo%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-md-3">
						<label for="fechaInicio">Desde:</label> <input type="text"
							name="fechaInicio" id="fechaInicio" class="form-control"
							value="<%=request.getParameter("fechaInicio") != null ? request.getParameter("fechaInicio") : ""%>">
					</div>
					<div class="col-md-3">
						<label for="fechaFin">Hasta:</label> <input type="text"
							name="fechaFin" id="fechaFin" class="form-control"
							value="<%=request.getParameter("fechaFin") != null ? request.getParameter("fechaFin") : ""%>">
					</div>
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary w-100 mt-4">Buscar</button>
					</div>

				</div>
			</form>
			<!-- Tabla de datos -->
			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Cargo</th>
						<th>Fecha Contratación</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					String filtro = request.getParameter("filtro");
					String cargo = request.getParameter("cargo");
					String fechaInicio = request.getParameter("fechaInicio");
					String fechaFin = request.getParameter("fechaFin");
					List<PersonalAdministrativo> lista = model.buscarAvanzado(filtro, cargo, fechaInicio, fechaFin);

					if (lista != null && !lista.isEmpty()) {
						for (PersonalAdministrativo personal : lista) {
					%>
					<tr>
						<td><%=personal.getPersonalID()%></td>
						<td><%=personal.getNombres()%></td>
						<td><%=personal.getApellidos()%></td>
						<td><%=personal.getCargo()%></td>
						<td><%=personal.getFechaContratacion()%></td>
						<td><a
							href="PersonalAdministrativoServlet?accion=editar&id=<%=personal.getPersonalID()%>">
								<img alt="Editar" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> | <a
							href="PersonalAdministrativoServlet?accion=eliminar&id=<%=personal.getPersonalID()%>"
							onclick="return confirm('¿Estás seguro de eliminar este registro?');">
								<img alt="Eliminar" src="image/ic_delete.svg" width="15"
								height="15" title="Eliminar">
						</a> </td>

					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="6" style="text-align: center;">No se encontraron
							registros.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</section>
</main>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#fechaInicio, #fechaFin").datepicker({
			dateFormat : "yy-mm-dd",
			changeMonth : true,
			changeYear : true
		});
	});
</script>
<%@ include file="../footer.jsp"%>

