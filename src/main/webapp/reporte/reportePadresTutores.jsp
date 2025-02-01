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
		<a class="btn btn-danger"
			href="PadresTutoresServlet?tipo=reporte">Generar Reporte</a>

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
