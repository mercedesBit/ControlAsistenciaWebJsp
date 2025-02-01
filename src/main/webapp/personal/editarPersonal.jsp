<%@ page import="Entidades.PersonalAdministrativoModel"%>
<%@ page import="entidades.PersonalAdministrativo"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Personal Administrativo</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<c:if test="${not empty error}">
				<div style="color: red;">${error}</div>
			</c:if>

			<form action="PersonalAdministrativoServlet" method="post">
				<!-- Se utiliza un campo oculto para indicar que es una acción de editar -->
				<input type="hidden" name="accion" value="editar"> <input
					type="hidden" name="personalID" value="${personal.personalID}">

				<div class="form-group">
					<label for="tipoDocumento">Tipo de Documento:</label> <input
						type="text" id="tipoDocumento" name="tipoDocumento"
						value="${personal.tipoDocumento}" class="form-control"  required>
				</div>

				<div class="form-group">
					<label for="numeroDocumento">Número de Documento:</label> <input
						type="text" id="numeroDocumento" name="numeroDocumento"
						value="${personal.numeroDocumento}" class="form-control"  required>
				</div>

				<div class="form-group">
					<label for="nombres">Nombres:</label> <input type="text"
						id="nombres" name="nombres" value="${personal.nombres}" class="form-control"  required>
				</div>

				<div class="form-group">
					<label for="apellidos">Apellidos:</label> <input type="text"
						id="apellidos" name="apellidos" value="${personal.apellidos}" class="form-control" 
						required>
				</div>

				<div class="form-group">
					<label for="correoElectronico">Correo Electrónico:</label> <input
						type="email" id="correoElectronico" name="correoElectronico" class="form-control" 
						value="${personal.correoElectronico}" required>
				</div>

				<div class="form-group">
					<label for="telefonoMovil">Teléfono Móvil:</label> <input
						type="text" id="telefonoMovil" name="telefonoMovil" class="form-control" 
						value="${personal.telefonoMovil}" required>
				</div>

				<div class="form-group">
					<label for="telefonoTrabajo">Teléfono de Trabajo:</label> <input
						type="text" id="telefonoTrabajo" name="telefonoTrabajo" class="form-control" 
						value="${personal.telefonoTrabajo}" required>
				</div>

				<div class="form-group">
					<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
						type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" 
						value="${personal.fechaNacimiento}" required>
				</div>

				<div class="form-group">
					<label for="direccion">Dirección:</label> <input type="text" class="form-control" 
						id="direccion" name="direccion" value="${personal.direccion}"
						required>
				</div>

				<div class="form-group">
					<label for="cargo">Cargo:</label> <input type="text" id="cargo" class="form-control" 
						name="cargo" value="${personal.cargo}" required>
				</div>

				<div class="form-group">
					<label for="fechaContratacion">Fecha de Contratación:</label> <input
						type="date" id="fechaContratacion" name="fechaContratacion" class="form-control" 
						value="${personal.fechaContratacion}" required>
				</div>

				<div class="form-group">
					<label for="estado">Estado:</label> <input type="text" id="estado" class="form-control" 
						name="estado" value="${personal.estado}" required>
				</div>

				<div class="form-group">
					<label for="sueldo">Sueldo:</label> <input type="number" class="form-control" 
						step="0.01" id="sueldo" name="sueldo" value="${personal.sueldo}"
						required>
				</div>

				<div class="form-group">
					<label for="redesSociales">Redes Sociales:</label> <input
						type="text" id="redesSociales" name="redesSociales" class="form-control" 
						value="${personal.redesSociales}" required>
				</div>

				<div class="form-group">
					<label for="fechaActualizacion">Fecha de Actualización:</label> <input
						type="date" id="fechaActualizacion" name="fechaActualizacion" class="form-control" 
						value="${personal.fechaActualizacion}" required>
				</div>

				<button type="submit" class="btn btn-danger">Guardar Cambios</button>
			</form>

		</div>
	</section>

</main>
<%@ include file="../footer.jsp"%>

