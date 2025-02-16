<%@page import="java.util.List"%>
<%@page import="entidades.Curso"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Curso</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<%
                Curso curso = (Curso)request.getAttribute("curso");            
            %>

			<form action="cursoServlet" method="post">
				<input type="hidden" name="idCurso"
					value="<%=(curso != null)? curso.getCursoID():"" %>">

				<div class="form-group">
					<label class="text-secondary">Código del Curso</label> <input
						class="form-control" type="text" name="txtCodigoCurso"
						value="<%=(curso != null)? curso.getCodigoCurso():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombre del Curso</label> <input
						class="form-control" type="text" name="txtNombreCurso"
						value="<%=(curso != null)? curso.getNombreCurso():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Descripción</label> <input
						class="form-control" type="text" name="txtDescripcion"
						value="<%=(curso != null)? curso.getDescripcion():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Duración</label> <input
						class="form-control" type="text" name="txtDuracion"
						value="<%=(curso != null)? curso.getCreditos():""%>" readonly>
				</div>

				

				<div class="form-group">
					<label class="text-secondary">Nivel</label> <input
						class="form-control" type="text" name="txtNivel"
						value="<%=(curso != null)? curso.getNivel():""%>" readonly>
				</div>
				
				
				
				<div class="form-group">
					<label class="text-secondary">Ciclo</label> <input
						class="form-control" type="text" name="txtGrado"
						value="<%=(curso != null)? curso.getCiclo():""%>" readonly>
				</div>
				
			
			




				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(curso != null)? curso.getEstado():""%>" readonly>
				</div>





				
				<div class="form-group">
					<label class="text-secondary">Notas</label> <input
						class="form-control" type="text" name="txtNotas"
						value="<%=(curso != null)? curso.getNotas():""%>" readonly>
				</div>

				

				<div class="form-group">
					<label class="text-secondary">Fecha de Registro</label> <input
						class="form-control" type="date" name="txtFechaRegistro"
						value="<%=(curso != null)? curso.getFechaRegistro() != null ? curso.getFechaRegistro().toString() : "" : "" %>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Usuario de Registro</label> <input
						class="form-control" type="text" name="txtUsuarioRegistro"
						value="<%=(curso != null)? curso.getUsuarioRegistro():""%>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Actualización</label> <input
						class="form-control" type="date" name="txtFechaActualizacion"
						value="<%=(curso != null)? curso.getFechaActualizacion() != null ? curso.getFechaActualizacion().toString() : "" : "" %>"
						readonly>
				</div>

				<br> <a class="btn btn-primary" href="CursoServlet?tipo=listar"
					role="button">Regresar al Listado</a>

			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>

