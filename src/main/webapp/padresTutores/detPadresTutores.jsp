<%@page import="entidades.PadresTutores"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Padres Tutores</h1>
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
                PadresTutores tutor = (PadresTutores)request.getAttribute("tutorData");            
            %>

			<form action="PadresTutoresServlet" method="post">
				<input type="hidden" name="idTutor"
					value="<%=(tutor != null)? tutor.getTutorID():"" %>">

				<div class="form-group">
					<label class="text-secondary">Tipo de Documento</label> <input
						class="form-control" type="text" name="txtTipoDocumento"
						value="<%=(tutor != null)? tutor.getTipoDocumento():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Número de Documento</label> <input
						class="form-control" type="text" name="txtNumeroDocumento"
						value="<%=(tutor != null)? tutor.getNumeroDocumento():""%>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombres</label> <input
						class="form-control" type="text" name="txtNombres"
						value="<%=(tutor != null)? tutor.getNombres():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Apellidos</label> <input
						class="form-control" type="text" name="txtApellidos"
						value="<%=(tutor != null)? tutor.getApellidos():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Correo Electrónico</label> <input
						class="form-control" type="email" name="txtCorreoElectronico"
						value="<%=(tutor != null)? tutor.getCorreoElectronico():""%>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono Móvil</label> <input
						class="form-control" type="text" name="txtTelefonoMovil"
						value="<%=(tutor != null)? tutor.getTelefonoMovil():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono Casa</label> <input
						class="form-control" type="text" name="txtTelefonoCasa"
						value="<%=(tutor != null)? tutor.getTelefonoCasa():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Dirección</label> <input
						class="form-control" type="text" name="txtDireccion"
						value="<%=(tutor != null)? tutor.getDireccion():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Relación con el Estudiante</label> <input
						class="form-control" type="text" name="txtRelacionEstudiante"
						value="<%=(tutor != null)? tutor.getRelacionEstudiante():""%>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Nacimiento</label> <input
						class="form-control" type="date" name="txtFechaNacimiento"
						value="<%=(tutor != null)? tutor.getFechaNacimiento() != null ? tutor.getFechaNacimiento().toString() : "" : "" %>"
						readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Ocupación</label> <input
						class="form-control" type="text" name="txtOcupacion"
						value="<%=(tutor != null)? tutor.getOcupacion():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(tutor != null)? tutor.getEstado():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Redes Sociales</label> <input
						class="form-control" type="text" name="txtRedesSociales"
						value="<%=(tutor != null)? tutor.getRedesSociales():""%>" readonly>
				</div>

				<br> <a class="btn btn-primary"
					href="PadresTutoresServlet?tipo=list" role="button">Regresar
					Listado</a>
			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>