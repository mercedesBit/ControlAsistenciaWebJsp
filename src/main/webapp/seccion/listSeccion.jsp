<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Seccion"%>
<%@page import="java.util.List"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Listar Seccion</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<a href="SeccionServlet?accion=nuevo" class="btn btn-primary">Crear
			Nuevo</a>
		<div class="row">
			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre Sección</th>
						<th>Número de Aula</th>
						<th>Estado</th>
						<th>Fecha de Registro</th>
						<th>Usuario de Registro</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
						@SuppressWarnings("unchecked")
                        List<Seccion> listSeccion = (List<Seccion>)request.getAttribute("secciones");
                        if(listSeccion != null){
                            for(Seccion seccion : listSeccion){
                    %>
					<tr>
						<td><%=seccion.getSeccionID()%></td>
						<td><%=seccion.getNombreSeccion()%></td>
						<td><%=seccion.getNumeroAula()%></td>
						<td><%=seccion.getEstado()%></td>
						<td><%=seccion.getFechaRegistro()%></td>
						<td><%=seccion.getUsuarioRegistro()%></td>
						<td><a
							href="SeccionServlet?accion=detalle&id=<%=seccion.getSeccionID()%>">
								<img alt="" src="image/ic_info.svg" width="15" height="15"
								title="Detalle">
						</a> <a href="SeccionServlet?accion=editar&id=<%=seccion.getSeccionID()%>">
								<img alt="" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> <a href="SeccionServlet?accion=eliminar&id=<%=seccion.getSeccionID()%>"
							onclick="return confirm('¿Está seguro de eliminar esta sección?')">
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
