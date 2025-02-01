<%@page import="entidades.Estudiante"%>
<%@page import="java.util.Date"%>
<%@page import="Entidades.PadresTutoresModel"%>
<%@page import="entidades.PadresTutores"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Estudiante</h1>
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
                Estudiante estudiante = (Estudiante)request.getAttribute("estudianteData");            
            %>

			<form action="EstudianteServlet" method="post">
				<input type="hidden" name="idEstudiante"
					value="<%=(estudiante != null)? estudiante.getEstudianteID():"" %>">

				<div class="form-group">
					<label class="text-secondary">Tipo de Documento</label> <input
						class="form-control" type="text" name="txtTipoDocumento"
						value="<%=(estudiante != null)? estudiante.getTipoDocumento():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Número de Documento</label> <input
						class="form-control" type="text" name="txtNumeroDocumento"
						value="<%=(estudiante != null)? estudiante.getNumeroDocumento():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombres</label> <input
						class="form-control" type="text" name="txtNombres"
						value="<%=(estudiante != null)? estudiante.getNombres():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Apellidos</label> <input
						class="form-control" type="text" name="txtApellidos"
						value="<%=(estudiante != null)? estudiante.getApellidos():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Nacimiento</label> <input
						class="form-control" type="date" name="txtFechaNacimiento"
						value="<%=(estudiante != null)? estudiante.getFechaNacimiento() != null ? estudiante.getFechaNacimiento().toString() : "" : "" %>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Género</label> <input
						class="form-control" type="text" name="txtGenero"
						value="<%=(estudiante != null)? estudiante.getGenero():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Email</label> <input
						class="form-control" type="email" name="txtEmail"
						value="<%=(estudiante != null)? estudiante.getEmail():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Teléfono</label> <input
						class="form-control" type="text" name="txtTelefono"
						value="<%=(estudiante != null)? estudiante.getTelefono():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Dirección</label> <input
						class="form-control" type="text" name="txtDireccion"
						value="<%=(estudiante != null)? estudiante.getDireccion():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado Civil</label> <input
						class="form-control" type="text" name="txtEstadoCivil"
						value="<%=(estudiante != null)? estudiante.getEstadoCivil():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Ingreso</label> <input
						class="form-control" type="date" name="txtFechaIngreso"
						value="<%=(estudiante != null)? estudiante.getFechaIngreso() != null ? estudiante.getFechaIngreso().toString() : "" : "" %>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(estudiante != null)? estudiante.getEstado():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Redes Sociales</label> <input
						class="form-control" type="text" name="txtRedesSociales"
						value="<%=(estudiante != null)? estudiante.getRedesSociales():""%>" readonly>
				</div>

				<div class="form-group">
					<label class="text-secondary">Emergencia Contacto</label> <input
						class="form-control" type="text" name="txtEmergenciaContacto"
						value="<%=(estudiante != null)? estudiante.getEmergenciaContacto():""%>" readonly>
				</div>

				<!-- Información sobre el Tutor -->
				<div class="form-group">
					<label class="text-secondary">Tutor</label> <select id="txtTutorID"
						name="txtTutorID" class="form-control" disabled>
						<option value="" >Selecciona una opción</option>
						<%
						int selectedTutorID = (estudiante != null && estudiante.getTutor() != null) ? estudiante.getTutor().getTutorID() : 0;

						PadresTutoresModel model = new PadresTutoresModel();
						List<PadresTutores> lista = model.listPadresTutores();
						for (PadresTutores obj : lista) {
							int tutorID = obj.getTutorID();
						%>
						<option value="<%=tutorID%>"
							<%=tutorID==selectedTutorID ? "selected" : ""%>>
							<%=obj.getNombres() + " " + obj.getApellidos()%>
						</option>
						<%
						}
						%>
					</select> 
				</div>

				<br> <a class="btn btn-primary"
					href="EstudianteServlet?tipo=list" role="button">Regresar
					Listado</a>
			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
