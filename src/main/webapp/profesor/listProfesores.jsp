<%@page import="entidades.Profesor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>


<main id="main" class="main">

	<div class="pagetitle">
		<h1>Profesores</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<a class="btn btn-primary" href="profesor/regProfesores.jsp" role="button">Crear Nuevo</a>
		<div class="row">
			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tipo Documento</th>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Fecha Nacimiento</th>
						<th>Email</th>
						<th>Acciones</th>
					</tr>
				</thead>

				<tbody>
					<%
					@SuppressWarnings("unchecked")
                        List<Profesor> listaProfesores = (List<Profesor>)request.getAttribute("lista");
                    
                        if(listaProfesores != null){
                            for(Profesor item : listaProfesores){
                    %>
					<tr>
						<td><%=item.getProfesorID() %></td>
						<td><%=item.getTipoDocumento() %></td>
						<td><%=item.getNombres() %></td>
						<td><%=item.getApellidos() %></td>
						<td><%=item.getFechaNacimiento() != null ? item.getFechaNacimiento().toString() : "" %></td>
						<td><%=item.getCorreoInstitucional() %></td>
						<td><a
							href="ProfesorServlet?tipo=info&id=<%=item.getProfesorID()%>">
								<img alt="" src="image/ic_info.svg" width="15" height="15"
								title="Detalle">
						</a> <a href="ProfesorServlet?tipo=modif&id=<%=item.getProfesorID()%>">
								<img alt="" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> <a
							href="ProfesorServlet?tipo=delete&id=<%=item.getProfesorID()%>">
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
			<br>


		</div>
	</section>

</main>
<%@ include file="../footer.jsp"%>