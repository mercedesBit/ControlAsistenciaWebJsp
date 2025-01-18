<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Curso</h1>
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
				<div class="card-body">
					<input type="hidden" name="accion" value="actualizar"> <input
						type="hidden" name="cursoID" value="${curso.cursoID}">

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="codigoCurso" class="form-label">Código del
								Curso</label> <input type="text" class="form-control" id="codigoCurso"
								name="codigoCurso" value="${curso.codigoCurso}" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="nombreCurso" class="form-label">Nombre del
								Curso</label> <input type="text" class="form-control" id="nombreCurso"
								name="nombreCurso" value="${curso.nombreCurso}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="descripcion" class="form-label">Descripción</label>
							<textarea class="form-control" id="descripcion"
								name="descripcion" required>${curso.descripcion}</textarea>
						</div>

						<div class="col-md-6 mb-3">
							<label for="duracion" class="form-label">Duración (horas)</label>
							<input type="number" class="form-control" id="duracion"
								name="duracion" value="${curso.duracion}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="grado" class="form-label">Grado</label> <input
								type="text" class="form-control" id="grado" name="grado"
								value="${curso.grado}" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="nivel" class="form-label">Nivel</label> <input
								type="text" class="form-control" id="nivel" name="nivel"
								value="${curso.nivel}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="modalidad" class="form-label">Modalidad</label> <input
								type="text" class="form-control" id="modalidad" name="modalidad"
								value="${curso.modalidad}" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="estado" class="form-label">Estado</label> <select
								class="form-select" id="estado" name="estado" required>
								<option value="Activo"
									${curso.estado == 'Activo' ? 'selected' : ''}>Activo</option>
								<option value="Inactivo"
									${curso.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="fechaInicio" class="form-label">Fecha de
								Inicio</label> <input type="date" class="form-control" id="fechaInicio"
								name="fechaInicio"
								value="${curso.fechaInicio}"
								required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="fechaFin" class="form-label">Fecha de Fin</label> <input
								type="date" class="form-control" id="fechaFin" name="fechaFin"
								value="${curso.fechaFin}"
								required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="requisitosPrevios" class="form-label">Requisitos
								Previos</label>
							<textarea class="form-control" id="requisitosPrevios"
								name="requisitosPrevios" required>${curso.requisitosPrevios}</textarea>
						</div>

						<div class="col-md-6 mb-3">
							<label for="cantidadMaximaEstudiantes" class="form-label">Máxima
								Cantidad de Estudiantes</label> <input type="number"
								class="form-control" id="cantidadMaximaEstudiantes"
								name="cantidadMaximaEstudiantes"
								value="${curso.cantidadMaximaEstudiantes}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="temario" class="form-label">Temario</label>
							<textarea class="form-control" id="temario" name="temario"
								required>${curso.temario}</textarea>
						</div>

						<div class="col-md-6 mb-3">
							<label for="horario" class="form-label">Horario</label>
							<textarea class="form-control" id="horario" name="horario"
								required>${curso.horario}</textarea>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="notas" class="form-label">Notas</label>
							<textarea class="form-control" id="notas" name="notas">${curso.notas}</textarea>
						</div>

						<div class="col-md-6 mb-3">
							<label for="profesorID" class="form-label">Profesor ID</label> <input
								type="number" class="form-control" id="profesorID"
								name="profesorID" value="${curso.profesorID}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="fechaRegistro" class="form-label">Fecha de
								Registro</label> <input type="date" class="form-control"
								id="fechaRegistro" name="fechaRegistro"
								value="${curso.fechaRegistro}"
								>
						</div>

						<div class="col-md-6 mb-3">
							<label for="usuarioRegistro" class="form-label">Usuario
								de Registro</label> <input type="text" class="form-control"
								id="usuarioRegistro" name="usuarioRegistro"
								value="${curso.usuarioRegistro}" >
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="fechaActualizacion" class="form-label">Fecha
								de Actualización</label> <input type="text" class="form-control"
								id="fechaActualizacion" name="fechaActualizacion"
								value="${curso.fechaActualizacion}"	>
						</div>
						<div class="col-md-6 mb-3">
							<label for="seccionID" class="form-label">Seccion ID</label> <input
								type="number" class="form-control" id="seccionID"
								name="seccionID" value="${curso.seccionID}" required>
						</div>
						
					</div>
				</div>

				<div class="card-footer">
					<div class="row">
						<div class="col-md-6">
							<button type="submit" class="btn btn-danger">
								<i class="fas fa-save"></i> Actualizar
							</button>
					
							<a href="CursoServlet?accion=listar"
								class="btn btn-secondary"> <i class="fas fa-times"></i>
								Cancelar
							</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>