<%@page import="entidades.PadresTutores"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Lista Padres Tutores</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
	<a class="btn btn-primary" href="padresTutores/regPadresTutores.jsp"
				role="button">Crear Nuevo</a>
		<div class="row">

			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tipo Documento</th>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Correo Electrónico</th>
						<th>Teléfono Móvil</th>
						<th>Acciones</th>
					</tr>
				</thead>

				<tbody>
					<%
					@SuppressWarnings("unchecked")
                        List<PadresTutores> listaTutores = (List<PadresTutores>)request.getAttribute("lista");
                    
                        if(listaTutores != null){
                            for(PadresTutores item : listaTutores){
                    %>
					<tr>
						<td><%=item.getTutorID() %></td>
						<td><%=item.getTipoDocumento() %></td>
						<td><%=item.getNombres() %></td>
						<td><%=item.getApellidos() %></td>
						<td><%=item.getCorreoElectronico() %></td>
						<td><%=item.getTelefonoMovil() %></td>
						<td><a
							href="PadresTutoresServlet?tipo=info&id=<%=item.getTutorID()%>">
								<img alt="" src="image/ic_info.svg" width="15" height="15"
								title="Detalle">
						</a> <a
							href="PadresTutoresServlet?tipo=modif&id=<%=item.getTutorID()%>">
								<img alt="" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> <a
							href="PadresTutoresServlet?tipo=delete&id=<%=item.getTutorID()%>">
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
