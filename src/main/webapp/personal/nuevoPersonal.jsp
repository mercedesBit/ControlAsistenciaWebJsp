<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="entidades.PersonalAdministrativo"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="modelo.PersonalAdministrativoModel"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registrar Personal Administrativo</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<form action="PersonalAdministrativoServlet" method="post">
				<input type="hidden" name="accion" value="guardar">

				<div class="form-group">
					<label for="personalID">ID Personal:</label> <input type="text"
						name="personalID" id="personalID" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="tipoDocumento">Tipo de Documento:</label> <input
						type="text" name="tipoDocumento" id="tipoDocumento" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="numeroDocumento">Número de Documento:</label> <input
						type="text" name="numeroDocumento" id="numeroDocumento" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="nombres">Nombres:</label> <input type="text"
						name="nombres" id="nombres" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="apellidos">Apellidos:</label> <input type="text"
						name="apellidos" id="apellidos" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="correoElectronico">Correo Electrónico:</label> <input
						type="email" name="correoElectronico" id="correoElectronico" class="form-control"
						required>
				</div>

				<div class="form-group">
					<label for="telefonoMovil">Teléfono Móvil:</label> <input
						type="text" name="telefonoMovil" id="telefonoMovil" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="telefonoTrabajo">Teléfono de Trabajo:</label> <input
						type="text" name="telefonoTrabajo" id="telefonoTrabajo" class="form-control">
				</div>

				<div class="form-group">
					<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
						type="date" name="fechaNacimiento" id="fechaNacimiento" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="direccion">Dirección:</label> <input type="text"
						name="direccion" id="direccion" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="cargo">Cargo:</label> <input type="text" name="cargo"
						id="cargo" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="fechaContratacion">Fecha de Contratación:</label> <input
						type="date" name="fechaContratacion" id="fechaContratacion" class="form-control"
						required>
				</div>

				<div class="form-group">
					<label for="estado">Estado:</label> <input type="text"
						name="estado" id="estado" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="sueldo">Sueldo:</label> <input type="number"
						step="0.01" name="sueldo" id="sueldo" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="redesSociales">Redes Sociales:</label> <input
						type="text" name="redesSociales" id="redesSociales" class="form-control">
				</div>

				<div class="form-group">
					<label for="fechaRegistro">Fecha de Registro:</label> <input
						type="date" name="fechaRegistro" id="fechaRegistro"
						value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" class="form-control"
						required>
				</div>

				<div class="form-group">
					<label for="usuarioRegistro">Usuario que Registra:</label> <input
						type="text" name="usuarioRegistro" id="usuarioRegistro" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="fechaActualizacion">Fecha de Actualización:</label> <input
						type="date" name="fechaActualizacion" id="fechaActualizacion" class="form-control"
						value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>"
						required>
				</div>
				<br/>
				<button type="submit" class="btn btn-danger">Guardar</button>
				<a href="PersonalAdministrativoServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
