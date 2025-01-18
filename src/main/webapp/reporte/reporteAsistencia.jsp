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
			<hr>

		<div class="col-12 col-lg-12">
				<h3>Generar Reporte</h3>

				<form action="AsistenciaServlet" method="post">
					<input type="hidden" name="tipo" value="reporte">

					<div class="mb-3">
						<label for="estado" class="form-label">Tipo de Asistencia</label> <select
							class="form-control" id="estado" name="estado" required>
							    <option value="Presente">Presente</option>
								<option value="Ausente">Ausente</option>
								<option value="Ausente Justificado">Ausente Justificado</option>
								<option value="Llego Tarde">Llegó Tarde</option>
								<option value="Evadio">Evadió</option>
						</select>
					</div>

					<div class="mb-3">
						<label for="fechaReporte" class="form-label">Fecha</label> <input
							type="date" class="form-control" id="fechaReporte"
							name="fechaReporte" required>
					</div>

					<button type="submit" class="btn btn-danger">Generar
						Reporte</button>
				</form>
			</div>
		</div>

	
	</section>
</main>

<%@ include file="../footer.jsp"%>
