<%@page import="entidades.Usuario"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.MySqlRolDAO"%>
<%@page import="entidades.Rol"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Modificar usuario</h1>
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
			Usuario usuario = (Usuario) request.getAttribute("usuarioData");
			%>

			<form action="ServletUsuarios?tipo=edit" method="post">
				<input type="hidden" name="txtidUsuario"
					value="<%=(usuario != null) ? usuario.getUsuarioID() : ""%>">

				<div class="form-group">
					<label class="text-secondary">Nombre de usuario</label> <input
						class="form-control" type="text" name="txtNombreUsuario"
						value="<%=(usuario != null) ? usuario.getNombreUsuario() : ""%>">
				</div>

				<div class="form-group">
					<label class="text-secondary">Contraseña</label> <input
						class="form-control" type="text" name="txtContrasena"
						value="<%=(usuario != null) ? usuario.getContrasena() : ""%>">
				</div>

				<!-- Información sobre el rol del usuario -->
				<div class="form-group">
					<label class="text-secondary">Rol</label> <select id="txtRoleID"
						name="txtRoleID" class="form-control">
						<option value="">Selecciona rol</option>
						<%
						int selectedRoleID= (usuario != null && usuario.getRol() != null) ? usuario.getRol().getRoleID() : 0;

						MySqlRolDAO model = new MySqlRolDAO();
						List<Rol> lista = model.listRol();
						for (Rol obj : lista) {
							int roleID = obj.getRoleID();
						%>
						<option value="<%=roleID%>"
							<%=roleID==selectedRoleID ? "selected" : ""%>>
							<%=obj.getNombreRole()%>
						</option>
						<%
						}
						%>
					</select> 
				</div>
				
				<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(usuario != null) ? usuario.getEstado() : ""%>">
				</div>

				<br>
				<div class="button">
					<input class="btn btn-danger" type="submit" >
					<a class="btn btn-secondary" href="ServletUsuarios?tipo=list">Regresar
						Listado</a>
				</div>

			</form>

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>
