<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Seccion</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<form action="SeccionServlet" method="post">
				<div class="card-body">
					<input type="hidden" name="accion" value="actualizar"> <input
						type="hidden" name="seccionID" value="${seccion.seccionID}">

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="nombreSeccion" class="form-label">Nombre de
								Sección</label> <input type="text" class="form-control"
								id="nombreSeccion" name="nombreSeccion"
								value="${seccion.nombreSeccion}" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="numeroAula" class="form-label">Número de Aula</label>
							<input type="text" class="form-control" id="numeroAula"
								name="numeroAula" value="${seccion.numeroAula}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="estado" class="form-label">Estado</label> <select
								class="form-select" id="estado" name="estado" required>
								<option value="Activo"
									${seccion.estado == 'Activo' ? 'selected' : ''}>Activo</option>
								<option value="Inactivo"
									${seccion.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
							</select>
						</div>

						<div class="col-md-6 mb-3">
							<label for="fechaRegistro" class="form-label">Fecha de
								Registro</label> <input type="date" class="form-control"
								id="fechaRegistro" name="fechaRegistro"
								value="${seccion.fechaRegistro}"
								readonly>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="usuarioRegistro" class="form-label">Usuario
								de Registro</label> <input type="text" class="form-control"
								id="usuarioRegistro" name="usuarioRegistro"
								value="${seccion.usuarioRegistro}" readonly>
						</div>

						<div class="col-md-6 mb-3">
							<label for="fechaActualizacion" class="form-label">Fecha
								de Actualización</label> <input type="date" class="form-control"
								id="fechaActualizacion" name="fechaActualizacion"
								value="${seccion.fechaActualizacion}"
								readonly>
						</div>
					</div>
				</div>

				<div class="card-footer">
					<div class="row">
						<div class="col-md-6">
							<button type="submit" class="btn btn-danger">
								<i class="fas fa-save"></i> Actualizar
							</button>
							<a href="SeccionServlet?accion=listar"
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

