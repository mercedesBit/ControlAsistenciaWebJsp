<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Curso"%>
<%@page import="java.util.List"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Listar Curso</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="actions">
			<a href="CursoServlet?accion=nuevo" class="btn btn-primary">Crear
				Nuevo</a>
		</div>
		<div class="row">
			<table class="table datatable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Codigo Curso</th>
						<th>Nombre Curso</th>
						<th>Duracion</th>
						<th>Estado</th>
						<th>Fecha de Registro</th>
						<th>Usuario de Registro</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
                        List<Curso> listCurso = (List<Curso>)request.getAttribute("cursos");
                    
                        if(listCurso != null){
                            for(Curso curso : listCurso){
                    %>
					<tr>
						<td><%=curso.getCursoID()%></td>
						<td><%=curso.getCodigoCurso()%></td>
						<td><%=curso.getNombreCurso()%></td>
						<td><%=curso.getDuracion()%></td>
						<td><%=curso.getEstado()%></td>
						<td><%=curso.getFechaRegistro()%></td>
						<td><%=curso.getUsuarioRegistro()%></td>
						<td><a href="CursoServlet?accion=detalle&id=<%=curso.getCursoID()%>">
								<img alt="" src="image/ic_info.svg" width="15" height="15"
								title="Detalle">
						</a> <a href="CursoServlet?accion=editar&id=<%=curso.getCursoID()%>"> <img
								alt="" src="image/ic_edit.svg" width="15" height="15"
								title="Editar">
						</a> <a href="CursoServlet?accion=eliminar&id=<%=curso.getCursoID()%>"
							onclick="return confirm(' Est  seguro de eliminar este curso?')">
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
