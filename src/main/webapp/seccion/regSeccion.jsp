<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<input type="hidden" name="accion" value="registrar">

				<div class="form-group">
					<label for="nombreSeccion">Nombre de Sección</label> <input
						type="text" class="form-control" id="nombreSeccion"
						name="nombreSeccion" required>
				</div>

				<div class="form-group">
					<label for="numeroAula">Número de Aula</label> <input type="text"
						class="form-control" id="numeroAula" name="numeroAula" required>
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
