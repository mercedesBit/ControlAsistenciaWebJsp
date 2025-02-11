<%@page import="modelo.PadresTutoresModel"%>
<%@page import="entidades.PadresTutores"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>Registrar Estudiante</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>

	</div>

	<section class="section dashboard">

		<div class="row">

			<form action="../EstudianteServlet?tipo=regist" method="post">
				<label>Tipo de documento</label>
					<select class="form-select" aria-label="Default select example">
					  <option value="1">DNI</option>
					  <option value="2">Carn� de extranjer�a</option>
					</select>
						
				
				<div class="form-group">
					<label>N�mero de Documento</label> <input class="form-control"
						type="text" name="txtNumeroDocumento">
				</div>
				<div class="form-group">
					<label>Nombres</label> <input class="form-control" type="text"
						name="txtNombres">
				</div>
				<div class="form-group">
					<label>Apellidos</label> <input class="form-control" type="text"
						name="txtApellidos">
				</div>
				<div class="form-group">
					<label>Fecha de Nacimiento</label> <input class="form-control"
						type="date" name="txtFechaNacimiento">
				</div>
				<div class="form-group">
					<label>G�nero</label>
					<select class="form-select" aria-label="Default select example">
					  <option value="1">Masculino</option>
					  <option value="2">Femenino</option>
					</select>
				</div>
				<div class="form-group">
					<label>Email</label> <input class="form-control" type="email"
						name="txtEmail">
				</div>
				<div class="form-group">
					<label>Tel�fono</label> <input class="form-control" type="text"
						name="txtTelefono">
				</div>
				<div class="form-group">
					<label>Direcci�n</label> <input class="form-control" type="text"
						name="txtDireccion">
				</div>

				<div class="form-group">
					<label>Fecha de Ingreso</label> <input class="form-control"
						type="date" name="txtFechaIngreso">
				</div>
				<div class="form-group">
					<label>Estado</label>
					<select class="form-select" aria-label="Default select example">
					  <option value="1">Matriculado</option>
					  <option value="2">Retirado</option>
					</select>
				</div>
				<div class="form-group">
					<label>Redes Sociales</label> <input class="form-control"
						type="text" name="txtRedesSociales">
				</div>
				<div class="form-group">
					<label>Emergencia Contacto</label> <input class="form-control"
						type="text" name="txtEmergenciaContacto">
				</div>
				<div class="form-group">
					<label>Id Tutor o Padre</label> <select id="txtTutorID"
						name="txtTutorID" class="form-control">
						<option value="">Selecciona una opci�n</option>
						<%
						PadresTutoresModel model = new PadresTutoresModel();
						List<PadresTutores> lista = model.listPadresTutores();
						for (PadresTutores obj : lista) {
						%>
						<option value="<%=obj.getTutorID()%>">
							<%=obj.getNombres() + " " + obj.getApellidos()%>
						</option>
						<%
						}
						%>
					</select>

				</div>
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" value="Registrar">
					<a href="../EstudianteServlet?tipo=list" role="button"
						class="btn btn-secondary">Listar</a>
				</div>
			</form>
		</div>
	</section>

</main>

<%@ include file="../footer.jsp"%>
