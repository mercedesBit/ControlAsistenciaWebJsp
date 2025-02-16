<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registro Padres Tutores</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">

			<form action="../PadresTutoresServlet?tipo=regist" method="post">
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
					<label>Correo Electrónico</label> <input class="form-control"
						type="email" name="txtCorreoElectronico" required>
				</div>
				<div class="form-group">
					<label>Teléfono Móvil</label> <input class="form-control"
						type="text" name="txtTelefonoMovil" required>
				</div>
				<div class="form-group">
					<label>Teléfono Casa</label> <input class="form-control"
						type="text" name="txtTelefonoCasa" required>
				</div>
				<div class="form-group">
					<label>Dirección</label> <input class="form-control" type="text"
						name="txtDireccion" required>
				</div>
				<div class="form-group">
					<label>Relación con el Estudiante</label> <input
						class="form-control" type="text" name="txtRelacionEstudiante" required>
				</div>
				<div class="form-group">
					<label>Fecha de Nacimiento</label> <input class="form-control"
						type="date" name="txtFechaNacimiento" required>
				</div>
				<div class="form-group">
					<label>Ocupación</label> <input class="form-control" type="text"
						name="txtOcupacion" required>
				</div>
				<div class="form-group">
					<label>Estado</label> <input class="form-control" type="text"
						name="txtEstado" required>
				</div>
				<div class="form-group">
					<label>Redes Sociales</label> <input class="form-control"
						type="text" name="txtRedesSociales" required>
				</div>
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" value="Registrar">
					<a href="../PadresTutoresServlet?tipo=list" role="button"
						class="btn btn-secondary">Listar</a>
				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
