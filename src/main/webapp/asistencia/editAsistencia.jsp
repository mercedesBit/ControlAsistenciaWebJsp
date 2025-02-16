<%@page import="java.util.List"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="java.util.Date"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Asistencia</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<form action="AsistenciaEstudianteServlet" method="post"
				accept-charset="UTF-8">
				<div class="card-body">
					<!-- Corregido: Nombre de variable EL -->
					<input type="hidden" name="tipo" value="actualizar"> <input
						type="hidden" name="asistenciaID" id="asistenciaID"
						value="${asistenciaEstudiante.asistenciaID}"> <input
						type="hidden" name="estudiante" id="estudiante"
						value="${asistenciaEstudiante.estudianteID}"> <input
						type="hidden" name="horario" id="horario"
						value="${asistenciaEstudiante.horarioID}">





					<div class="col-md-6 mb-3">
						<label for="estado" class="form-label">Estado </label> <select
							class="form-control" id="estado" name="estado" required>
							<option value="Asistencia"
								${asistenciaEstudiante.estadoAsistencia == 'Asistencia' ? 'selected' : ''}>Asistencia</option>
							<option value="Inasistencia"
								${asistenciaEstudiante.estadoAsistencia == 'Inasistencia' ? 'selected' : ''}>Inasistencia</option>

							<option value="En espera"
								${asistenciaEstudiante.estadoAsistencia == 'En espera' ? 'selected' : ''}>En
								espera</option>
							<option value="Inasistencia Justificada"
								${asistenciaEstudiante.estadoAsistencia == 'Inasistencia Justificada' ? 'selected' : ''}>Inasistencia
								Justificada</option>
						</select>
					</div>


					<div class="col-md-6 mb-3">
						<label for="comentario" class="form-label">Comentario</label> <input
							type="text" class="form-control" id="comentario"
							name="comentario" value="${asistenciaEstudiante.comentario}"
							required>
					</div>





					<!-- Corregido: Fecha de Clase -->
					<div class="col-md-6 mb-3">
						<label for="fechaDeClase" class="form-label">Fecha de
							Clase</label> <input type="date" class="form-control" id="fechaDeClase"
							name="fechaDeClase" value="${asistenciaEstudiante.fechaDeClase}"
							required>
					</div>
				</div>


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



				<div class="card-footer">


					<button type="submit" class="btn btn-danger">
						<i class="fas fa-save"></i> Actualizar
					</button>
					<a class="btn btn-primary"
						href="AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=${asistenciaEstudiante.horarioID}&estudianteID=${asistenciaEstudiante.estudianteID}">
						Cancelar </a>

				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
