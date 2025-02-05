<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registrar Seccion</h1>
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
				<input type="hidden" name="accion" value="registrar">

				<div class="row">
						<div class="col-md-6 mb-3">
							<label for="nombreSeccion" class="form-label">Nombre de
								Sección</label> <input type="text" class="form-control"
								id="nombreSeccion" name="nombreSeccion"
								required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="numeroAula" class="form-label">Número de Aula</label>
							<input type="text" class="form-control" id="numeroAula"
								name="numeroAula" required>
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
							<label for="usuarioRegistro" class="form-label">Usuario
								de Registro</label> <input type="text" class="form-control"
								id="usuarioRegistro" name="usuarioRegistro" value="<%= session.getAttribute("nombreUsuario") %>" readonly>
						</div>
					</div>
					<br/>
					<div class="form-actions">
						<button type="submit" class="btn btn-danger">Registrar</button>
						<a href="SeccionServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
					</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
