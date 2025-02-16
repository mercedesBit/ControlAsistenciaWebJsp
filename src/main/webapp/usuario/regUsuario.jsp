<%@page import="modelo.MySqlRolDAO"%>
<%@page import="entidades.Rol"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>Registrar usuario</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>

	</div>

	<section class="section dashboard">

		<div class="row">

			<form action="../ServletUsuarios?tipo=regist" method="post">
				
				<div class="form-group">
					<label>Nombre de usuario</label> <input class="form-control"
						type="text" name="txtNombreUsuario" required>
				</div>
				<div class="form-group">
					<label>Contraseña</label> <input class="form-control" type="text"
						name="txtContrasena" required>
				</div>

				<div class="form-group">
					<label>Rol</label> <select id="txtRoleID"
						name="txtRoleID" class="form-control" required>
						<option value="">Selecciona un Rol</option>
						<%
						 MySqlRolDAO model = new MySqlRolDAO();
						List<Rol> lista = model.listRol();
						for (Rol obj : lista) {
						%>
						<option value="<%=obj.getRoleID()%>">
							<%=obj.getNombreRole()%>
						</option>
						<%
						}
						%>
					</select>

				</div>
				<div class="form-group">
					<label>Estado</label> <input class="form-control" type="text"
						name="txtEstado" required>
				</div>
				<br>
				<div class="button">
					<input type="submit" class="btn btn-danger" value="Registrar" required>
					<a href="../ServletUsuarios?tipo=list" role="button"
						class="btn btn-secondary">Listar</a>
				</div>
			</form>
		</div>
	</section>

</main>

<%@ include file="../footer.jsp"%>
