<%@page import="java.util.List"%>
<%@page import="entidades.Curso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Reporte Curso</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
	        <a href="CursoReporteServlet?action=downloadPNG" class="btn btn-danger">Descargar en PNG</a>

		<div class="row">
        <table class="table datatable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Duración</th>
                    <th>Grado</th>
                    <th>Nivel</th>
                    <th>Modalidad</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                </tr>
            </thead>
            <tbody>
                <%
                	@SuppressWarnings("unchecked")
                    List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
                    if (cursos != null) {
                        for (Curso c : cursos) {
                %>
                <tr>
                    <td><%=c.getCursoID()%></td>
                    <td><%=c.getCodigoCurso()%></td>
                    <td><%=c.getNombreCurso()%></td>
                    <td><%=c.getDescripcion()%></td>
                    <td><%=c.getDuracion()%></td>
                    <td><%=c.getGrado()%></td>
                    <td><%=c.getNivel()%></td>
                    <td><%=c.getModalidad()%></td>
                    <td><%=c.getFechaRegistro()%></td>
                    <td><%=c.getFechaActualizacion()%></td>
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
