<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registrar Curso</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">

			<form action="CursoServlet" method="post">
				<input type="hidden" name="accion" value="registrar">

				<div class="form-group">
					<label for="codigoCurso">Código del Curso</label> <input
						type="text" class="form-control" id="codigoCurso"
						name="codigoCurso" required>
				</div>

				<div class="form-group">
					<label for="nombreCurso">Nombre del Curso</label> <input
						type="text" class="form-control" id="nombreCurso"
						name="nombreCurso" required>
				</div>

				<div class="form-group">
					<label for="descripcion">Descripción</label>
					<textarea class="form-control" id="descripcion" name="descripcion"
						required></textarea>
				</div>

				<div class="form-group">
					<label for="duracion">Duración (horas)</label> <input type="number"
						class="form-control" id="duracion" name="duracion" required>
				</div>

				<div class="form-group">
					<label for="grado">Grado</label> <input type="text"
						class="form-control" id="grado" name="grado" required>
				</div>

				<div class="form-group">
					<label for="nivel">Nivel</label> <input type="text"
						class="form-control" id="nivel" name="nivel" required>
				</div>

				<div class="form-group">
					<label for="modalidad">Modalidad</label> <input type="text"
						class="form-control" id="modalidad" name="modalidad" required>
				</div>
				
				<%--ELIMINAR ESTO.

				<div class="form-group">
					<label for="fechaInicio">Fecha de Inicio</label> <input type="date"
						class="form-control" id="fechaInicio" name="fechaInicio" required>
				</div>

				<div class="form-group">
					<label for="fechaFin">Fecha de Fin</label> <input type="date"
						class="form-control" id="fechaFin" name="fechaFin" required>
				</div>
				
					<div class="form-group">
					<label for="temario">Temario</label>
					<textarea class="form-control" id="temario" name="temario" required></textarea>
				</div>
				
				<div class="form-group">
					<label for="horario">Horario</label>
					<textarea class="form-control" id="horario" name="horario" required></textarea>
				</div>
				
					<div class="form-group">
					<label for="profesorID">Seccion ID</label> <input type="number"
						class="form-control" id="profesorID" name="profesorID" required>
				</div>
				<div class="form-group">
					<label for="seccionID">Profesor ID</label> <input type="number"
						class="form-control" id="seccionID" name="seccionID" required>
				</div>
    
    --%>
				<div class="form-group">
					<label for="requisitosPrevios">Requisitos Previos</label>
					<textarea class="form-control" id="requisitosPrevios"
						name="requisitosPrevios" required></textarea>
				</div>

				<div class="form-group">
					<label for="cantidadMaximaEstudiantes">Máxima Cantidad de
						Estudiantes</label> <input type="number" class="form-control"
						id="cantidadMaximaEstudiantes" name="cantidadMaximaEstudiantes"
						required>
				</div>

			

				

				<div class="form-group">
					<label for="notas">Notas</label>
					<textarea class="form-control" id="notas" name="notas"></textarea>
				</div>
			


				<br />
				<div class="form-actions">
					<button type="submit" class="btn btn-danger">Registrar</button>
					<a href="CursoServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>