<%@page import="entidades.Profesor"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Profesor</h1>
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
                Profesor profesor = (Profesor)request.getAttribute("profesorData");            
            %>

			<form action="ProfesorServlet?tipo=edit" method="post">
				<input type="hidden" name="profesorID"
					value="<%=(profesor != null)? profesor.getProfesorID():"" %>">

				<div class="form-group">
					<label class="text-secondary">Tipo de Documento</label> <input
						class="form-control" type="text" name="txtTipoDocumento"
						value="<%=(profesor != null)? profesor.getTipoDocumento():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Número de Documento</label> <input
						class="form-control" type="text" name="txtNumeroDocumento"
						value="<%=(profesor != null)? profesor.getNumeroDocumento():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombres</label> <input
						class="form-control" type="text" name="txtNombres"
						value="<%=(profesor != null)? profesor.getNombres():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Apellidos</label> <input
						class="form-control" type="text" name="txtApellidos"
						value="<%=(profesor != null)? profesor.getApellidos():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Nacimiento</label> <input
						class="form-control" type="date" name="txtFechaNacimiento"
						value="<%=(profesor != null)? profesor.getFechaNacimiento() != null ? profesor.getFechaNacimiento().toString() : "" : "" %>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Correo Institucional</label> <input
						class="form-control" type="email" name="txtCorreoInstitucional"
						value="<%=(profesor != null)? profesor.getCorreoInstitucional():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono Móvil</label> <input
						class="form-control" type="text" name="txtTelefonoMovil"
						value="<%=(profesor != null)? profesor.getTelefonoMovil():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono de Trabajo</label> <input
						class="form-control" type="text" name="txtTelefonoTrabajo"
						value="<%=(profesor != null)? profesor.getTelefonoTrabajo():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Contratación</label> <input
						class="form-control" type="date" name="txtFechaContratacion"
						value="<%=(profesor != null)? profesor.getFechaContratacion() != null ? profesor.getFechaContratacion().toString() : "" : "" %>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Especialidad</label> <input
						class="form-control" type="text" name="txtEspecialidad"
						value="<%=(profesor != null)? profesor.getEspecialidad():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Grado Académico</label> <input
						class="form-control" type="text" name="txtGradoAcademico"
						value="<%=(profesor != null)? profesor.getGradoAcademico():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Horas de Docencia</label> <input
						class="form-control" type="number" name="txtHorasDocencia"
						value="<%=(profesor != null)? profesor.getHorasDocencia():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(profesor != null)? profesor.getEstado():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Dirección</label> <input
						class="form-control" type="text" name="txtDireccion"
						value="<%=(profesor != null)? profesor.getDireccion():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Modalidad de Trabajo</label> <input
						class="form-control" type="text" name="txtModalidadTrabajo"
						value="<%=(profesor != null)? profesor.getModalidadTrabajo():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Redes Sociales</label> <input
						class="form-control" type="text" name="txtRedesSociales"
						value="<%=(profesor != null)? profesor.getRedesSociales():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Registro</label> <input
						class="form-control" type="date" name="txtFechaRegistro"
						value="<%=(profesor != null)? profesor.getFechaRegistro() != null ? profesor.getFechaRegistro().toString() : "" : "" %>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Usuario de Registro</label> <input
						class="form-control" type="text" name="txtUsuarioRegistro"
						value="<%=(profesor != null)? profesor.getUsuarioRegistro():""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Actualización</label> <input
						class="form-control" type="date" name="txtFechaActualizacion"
						value="<%=(profesor != null)? profesor.getFechaActualizacion() != null ? profesor.getFechaActualizacion().toString() : "" : "" %>">
				</div>

				<br>
				<div class="button">
					<input class="btn btn-danger" type="submit" value="Actualizar">
					<a class="btn btn-secondary" href="ProfesorServlet?tipo=list">Regresar
						Listado</a>
				</div>

			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
