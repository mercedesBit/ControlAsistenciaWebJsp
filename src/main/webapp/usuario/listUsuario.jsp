<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>Usuarios</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>

	</div>

	<section class="section dashboard">
	<a class="btn btn-primary" href="<%= request.getContextPath() %>/usuario/regUsuario.jsp"
				role="button">Nuevo usuario</a>
		<div class="row">
		
			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre de usuario</th>
						<th>Contraseña</th>
						<th>Rol</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>

				<tbody>
					<%
                        List<Usuario> listaUsuarios = (List<Usuario>)request.getAttribute("lista");
                    
                        if(listaUsuarios != null){
                            for(Usuario item : listaUsuarios){
                    %>
					<tr>
						<td><%=item.getUsuarioID() %></td>
						<td><%=item.getNombreUsuario()%></td>
						<td><%=item.getContrasena()%></td>
						<td><%=item.getRol().getNombreRole()%></td>
						<td><%=item.getEstado()%></td>

						<td><a
							href="ServletUsuarios?tipo=info&id=<%=item.getUsuarioID()%>">
								<img alt="" src="image/ic_info.svg" width="15" height="15"
								title="Detalle">
						</a> <a
							href="ServletUsuarios?tipo=modif&id=<%=item.getUsuarioID()%>">
								<img alt="" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> <a
							href="ServletUsuarios?tipo=delete&id=<%=item.getUsuarioID()%>">
								<img alt="" src="image/ic_delete.svg" width="15" height="15"
								title="Eliminar">
						</a></td>
					</tr>
					<%              
                            }
                        }
                    
                    %>
				</tbody>

			</table>


		</div>
	</section>

</main>

<%@ include file="../footer.jsp"%>