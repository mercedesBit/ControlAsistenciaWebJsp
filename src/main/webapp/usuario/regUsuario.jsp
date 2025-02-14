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

			<form action="../EstudianteServlet?tipo=regist" method="post">
				
				<div class="form-group">
					<label>Nombre de usuario</label> <input class="form-control"
						type="text" name="txtNombreUsuario">
				</div>
				<div class="form-group">
					<label>Contraseña</label> <input class="form-control" type="text"
						name="txtContraseña">
				</div>

				<div class="form-group">
					<label>Rol</label> <select id="txtTutorID"
						name="txtTutorID" class="form-control">
						<option value="">Rol</option>
						<%
						 MySqlRolDAO model = new MySqlRolDAO();
						List<Rol> lista = model.listRol();
						for (Rol obj : lista) {
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
