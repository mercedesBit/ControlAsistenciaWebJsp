<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Seccion</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<div class="card-body">
				<div class="row">
					<div class="col-md-6">
						<strong>ID de Sección:</strong>
						<p class="form-control">${seccion.seccionID}</p>
					</div>
					<div class="col-md-6">
						<strong>Nombre de Sección:</strong>
						<p class="form-control">${seccion.nombreSeccion}</p>
					</div>
				</div>

				<div class="row mt-3">
					<div class="col-md-6">
						<strong>Número de Aula:</strong>
						<p class="form-control">${seccion.numeroAula}</p>
					</div>
					<div class="col-md-6">
						<strong>Estado:</strong>
						<p class="form-control">${seccion.estado}</p>
					</div>
				</div>

				<div class="row mt-3">
					<div class="col-md-6">
						<strong>Fecha de Registro:</strong>
						<p class="form-control">${seccion.fechaRegistro} 
						</p>
					</div>
					<div class="col-md-6">
						<strong>Usuario de Registro:</strong>
						<p class="form-control">${seccion.usuarioRegistro}</p>
					</div>
				</div>

					<div class="row mt-3">
						<div class="col-md-12">
							<strong>Fecha de Actualización:</strong>
							<p class="form-control">${seccion.fechaActualizacion}
							</p>
						</div>
					</div>
			</div>

			<div class="card-footer">
				<div class="row">
					<div class="col-md-6">
						<a href="SeccionServlet?accion=editar&id=${seccion.seccionID}"
							class="btn btn-danger"> <i class="fas fa-edit"></i>
							Editar
						</a>
						<a href="SeccionServlet?accion=listar"
							class="btn btn-secondary btn-block"> <i
							class="fas fa-arrow-left"></i> Regresar
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>