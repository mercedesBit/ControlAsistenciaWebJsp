<%@page import="entidades.Estudiante"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.PadresTutoresModel"%>
<%@page import="entidades.PadresTutores"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar Estudiante</h1>
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
			Estudiante estudiante = (Estudiante) request.getAttribute("estudianteData");
			%>

			<form action="EstudianteServlet?tipo=edit" method="post">
				<input type="hidden" name="idEstudiante"
					value="<%=(estudiante != null) ? estudiante.getEstudianteID() : ""%>">

				<div class="form-group">
					<label class="text-secondary">Tipo de Documento</label>
						
					<select class="form-select" aria-label="Default select example" value="<%=(estudiante != null) ? estudiante.getTipoDocumento() : ""%>">

					  <option value="1">DNI</option>
					  <option value="2">Carn� de extranjer�a</option>
					</select>	
				</div>

				<div class="form-group">
					<label class="text-secondary">N�mero de Documento</label> <input
						class="form-control" type="text" name="txtNumeroDocumento"
						value="<%=(estudiante != null) ? estudiante.getNumeroDocumento() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Nombres</label> <input
						class="form-control" type="text" name="txtNombres"
						value="<%=(estudiante != null) ? estudiante.getNombres() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Apellidos</label> <input
						class="form-control" type="text" name="txtApellidos"
						value="<%=(estudiante != null) ? estudiante.getApellidos() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Nacimiento</label> <input
						class="form-control" type="date" name="txtFechaNacimiento"
						value="<%=(estudiante != null) ? estudiante.getFechaNacimiento() != null ? estudiante.getFechaNacimiento().toString() : ""
				: ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">G�nero</label> <input
						class="form-control" type="text" name="txtGenero"
						value="<%=(estudiante != null) ? estudiante.getGenero() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Email</label> <input
						class="form-control" type="email" name="txtEmail"
						value="<%=(estudiante != null) ? estudiante.getEmail() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Tel�fono</label> <input
						class="form-control" type="text" name="txtTelefono"
						value="<%=(estudiante != null) ? estudiante.getTelefono() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Direcci�n</label> <input
						class="form-control" type="text" name="txtDireccion"
						value="<%=(estudiante != null) ? estudiante.getDireccion() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Fecha de Ingreso</label> <input
						class="form-control" type="date" name="txtFechaIngreso"
						value="<%=(estudiante != null) ? estudiante.getFechaIngreso() != null ? estudiante.getFechaIngreso().toString() : "": ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(estudiante != null) ? estudiante.getEstado() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Redes Sociales</label> <input
						class="form-control" type="text" name="txtRedesSociales"
						value="<%=(estudiante != null) ? estudiante.getRedesSociales() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Emergencia Contacto</label> <input
						class="form-control" type="text" name="txtEmergenciaContacto"
						value="<%=(estudiante != null) ? estudiante.getEmergenciaContacto() : ""%>">
				</div>

				<!-- Informaci�n sobre el Tutor -->
				<div class="form-group">
					<label class="text-secondary">Tutor</label> <select id="txtTutorID"
						name="txtTutorID" class="form-control">
						<option value="">Selecciona una opci�n</option>
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

				<br>
				<div class="button">
					<input class="btn btn-danger" type="submit" value="Actualizar">
					<a class="btn btn-secondary" href="EstudianteServlet?tipo=list">Regresar
						Listado</a>
				</div>

			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
