<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="entidades.PersonalAdministrativo"%>
<%@ page import="Entidades.PersonalAdministrativoModel"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle Personal Administrativo</h1>
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
            // Recuperar el parámetro "id" de la solicitud
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                // Obtener el detalle del registro desde el modelo
                PersonalAdministrativoModel model = new PersonalAdministrativoModel();
                PersonalAdministrativo personal = model.obtenerPorID(id);
                if (personal != null) {
        %>
			<div class="field">
				<label>ID:</label> <span><%= personal.getPersonalID() %></span>
			</div>
			<div class="field">
				<label>Tipo Documento:</label> <span><%= personal.getTipoDocumento() %></span>
			</div>
			<div class="field">
				<label>Número Documento:</label> <span><%= personal.getNumeroDocumento() %></span>
			</div>
			<div class="field">
				<label>Nombres:</label> <span><%= personal.getNombres() %></span>
			</div>
			<div class="field">
				<label>Apellidos:</label> <span><%= personal.getApellidos() %></span>
			</div>
			<div class="field">
				<label>Correo Electrónico:</label> <span><%= personal.getCorreoElectronico() %></span>
			</div>
			<div class="field">
				<label>Teléfono Móvil:</label> <span><%= personal.getTelefonoMovil() %></span>
			</div>
			<div class="field">
				<label>Teléfono Trabajo:</label> <span><%= personal.getTelefonoTrabajo() %></span>
			</div>
			<div class="field">
				<label>Fecha Nacimiento:</label> <span><%= personal.getFechaNacimiento() %></span>
			</div>
			<div class="field">
				<label>Dirección:</label> <span><%= personal.getDireccion() %></span>
			</div>
			<div class="field">
				<label>Cargo:</label> <span><%= personal.getCargo() %></span>
			</div>
			<div class="field">
				<label>Fecha Contratación:</label> <span><%= personal.getFechaContratacion() %></span>
			</div>
			<div class="field">
				<label>Estado:</label> <span><%= personal.getEstado() %></span>
			</div>
			<div class="field">
				<label>Sueldo:</label> <span><%= personal.getSueldo() %></span>
			</div>
			<div class="field">
				<label>Redes Sociales:</label> <span><%= personal.getRedesSociales() %></span>
			</div>
			<div class="field">
				<label>Fecha Registro:</label> <span><%= personal.getFechaRegistro() %></span>
			</div>
			<div class="field">
				<label>Usuario Registro:</label> <span><%= personal.getUsuarioRegistro() %></span>
			</div>
			<div class="field">
				<label>Fecha Actualización:</label> <span><%= personal.getFechaActualizacion() %></span>
			</div>
			<%
                } else {
        %>
			<p>No se encontró información para el ID proporcionado.</p>
			<%
                }
            } else {
        %>
			<p>El ID no es válido.</p>
			<%
            }
        %>
			<a href="listarPersonal.jsp" class="back-btn">Volver al Listado</a>
		</div>
	</section>

</main>
<%@ include file="../footer.jsp"%>


