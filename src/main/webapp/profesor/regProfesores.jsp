<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registro Profesor</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<form action="../ProfesorServlet?tipo=regist" method="post">
				<div class="form-group">
					<label>Tipo de Documento</label> <input class="form-control"
						type="text" name="txtTipoDocumento" required>
				</div>
				<div class="form-group">
					<label>Número de Documento</label> <input class="form-control"
						type="text" name="txtNumeroDocumento" required>
				</div>
				<div class="form-group">
					<label>Nombres</label> <input class="form-control" type="text"
						name="txtNombres" required>
				</div>
				<div class="form-group">
					<label>Apellidos</label> <input class="form-control" type="text"
						name="txtApellidos" required>
				</div>
				<div class="form-group">
					<label>Fecha de Nacimiento</label> <input class="form-control"
						type="date" name="txtFechaNacimiento" required>
				</div>
				<div class="form-group">
					<label>Correo Institucional</label> <input class="form-control"
						type="email" name="txtCorreoInstitucional" required>
				</div>
				<div class="form-group">
					<label>Teléfono Móvil</label> <input class="form-control"
						type="text" name="txtTelefonoMovil" required>
				</div>
				<div class="form-group">
					<label>Teléfono de Trabajo</label> <input class="form-control"
						type="text" name="txtTelefonoTrabajo">
				</div>
				<div class="form-group">
					<label>Fecha de Contratación</label> <input class="form-control"
						type="date" name="txtFechaContratacion" required>
				</div>
				<div class="form-group">
					<label>Especialidad</label> <input class="form-control" type="text"
						name="txtEspecialidad" required>
				</div>
				<div class="form-group">
					<label>Grado Académico</label> <input class="form-control"
						type="text" name="txtGradoAcademico" required>
				</div>
				<div class="form-group">
					<label>Horas de Docencia</label> <input class="form-control"
						type="number" name="txtHorasDocencia" required>
				</div>
				<div class="form-group">
					<label>Estado</label> <input class="form-control" type="text"
						name="txtEstado" required>
				</div>
				<div class="form-group">
					<label>Dirección</label> <input class="form-control" type="text"
						name="txtDireccion" required>
				</div>
			<div class="form-group">
					<label>Modalidad de Trabajo</label> <input class="form-control"
						type="text" name="txtModalidadTrabajo" required>
				</div>
				<div class="form-group">
					<label>Redes Sociales</label> <input class="form-control"
						type="text" name="txtRedesSociales">
				</div>
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" value="Registrar">
					<a href="ProfesorServlet?tipo=list" role="button"
						class="btn btn-secondary">Listar</a>
				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
