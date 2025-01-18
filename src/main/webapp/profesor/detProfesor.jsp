<%@page import="entidades.Profesor"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Profesor</h1>
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

			<form action="ProfesorServlet" method="post">
				<input type="hidden" name="profesorID"
					value="<%=(profesor != null)? profesor.getProfesorID():"" %>">

				<div class="form-group">
					<label class="text-secondary">Tipo de Documento</label> <input
						class="form-control" type="text" name="txtTipoDocumento"
						value="<%=(profesor != null)? profesor.getTipoDocumento():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Número de Documento</label> <input
						class="form-control" type="text" name="txtNumeroDocumento"
						value="<%=(profesor != null)? profesor.getNumeroDocumento():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombres</label> <input
						class="form-control" type="text" name="txtNombres"
						value="<%=(profesor != null)? profesor.getNombres():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Apellidos</label> <input
						class="form-control" type="text" name="txtApellidos"
						value="<%=(profesor != null)? profesor.getApellidos():""%>" readonly> 
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Nacimiento</label> <input
						class="form-control" type="date" name="txtFechaNacimiento"
						value="<%=(profesor != null)? profesor.getFechaNacimiento() != null ? profesor.getFechaNacimiento().toString() : "" : "" %>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Correo Institucional</label> <input
						class="form-control" type="email" name="txtCorreoInstitucional"
						value="<%=(profesor != null)? profesor.getCorreoInstitucional():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono Móvil</label> <input
						class="form-control" type="text" name="txtTelefonoMovil"
						value="<%=(profesor != null)? profesor.getTelefonoMovil():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono de Trabajo</label> <input
						class="form-control" type="text" name="txtTelefonoTrabajo"
						value="<%=(profesor != null)? profesor.getTelefonoTrabajo():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Contratación</label> <input
						class="form-control" type="date" name="txtFechaContratacion"
						value="<%=(profesor != null)? profesor.getFechaContratacion() != null ? profesor.getFechaContratacion().toString() : "" : "" %>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Especialidad</label> <input
						class="form-control" type="text" name="txtEspecialidad"
						value="<%=(profesor != null)? profesor.getEspecialidad():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Grado Académico</label> <input
						class="form-control" type="text" name="txtGradoAcademico"
						value="<%=(profesor != null)? profesor.getGradoAcademico():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Horas de Docencia</label> <input
						class="form-control" type="number" name="txtHorasDocencia"
						value="<%=(profesor != null)? profesor.getHorasDocencia():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(profesor != null)? profesor.getEstado():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Dirección</label> <input
						class="form-control" type="text" name="txtDireccion"
						value="<%=(profesor != null)? profesor.getDireccion():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Modalidad de Trabajo</label> <input
						class="form-control" type="text" name="txtModalidadTrabajo"
						value="<%=(profesor != null)? profesor.getModalidadTrabajo():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Redes Sociales</label> <input
						class="form-control" type="text" name="txtRedesSociales"
						value="<%=(profesor != null)? profesor.getRedesSociales():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Registro</label> <input
						class="form-control" type="date" name="txtFechaRegistro"
						value="<%=(profesor != null)? profesor.getFechaRegistro() != null ? profesor.getFechaRegistro().toString() : "" : "" %>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Usuario de Registro</label> <input
						class="form-control" type="text" name="txtUsuarioRegistro"
						value="<%=(profesor != null)? profesor.getUsuarioRegistro():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Actualización</label> <input
						class="form-control" type="date" name="txtFechaActualizacion"
						value="<%=(profesor != null)? profesor.getFechaActualizacion() != null ? profesor.getFechaActualizacion().toString() : "" : "" %>" readonly>
				</div>

				<br> <a class="btn btn-primary"
					href="ProfesorServlet?tipo=list" role="button">Regresar Listado</a>
			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
