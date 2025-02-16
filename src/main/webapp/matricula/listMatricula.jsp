<%@page import="entidades.Estudiante"%>
<%@page import="entidades.Matricula"%>
<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">

    <div class="pagetitle">
        <h1>Lista de Estudiantes Matriculados</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                <li class="breadcrumb-item active">Matriculas</li>
            </ol>
        </nav>
    </div>

    <section class="section dashboard">
        <div class="row">
            <table class="table datatable">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Estudiante</th>
                        <th>Horario</th>
                        <th>Fecha Matrícula</th>
                        <th>Estado Matrícula</th>
                        <th>Observaciones</th>
                        <th>Modo Matrícula</th>
                        <th>Ciclo</th>
                    </tr>
                </thead>

                <tbody>
                    <%
	                    @SuppressWarnings("unchecked")
	                    List<Matricula> listaMatriculas = (List<Matricula>) request.getAttribute("listaMatricula");
	
	                    if (listaMatriculas != null && !listaMatriculas.isEmpty()) {
	                        for (Matricula item : listaMatriculas) {
	                %>
	                <tr>
	                    <td><%= item.getCodigoMatricula() %></td>
	                    <td><%= item.getEstudiante().getNombres() + " " + item.getEstudiante().getApellidos() %></td>
	                    <td><%= item.getHorario() != null ? "Curso: " + item.getHorario().getNombreCurso() + "     -     " +"\n Profesor: "+ item.getHorario().getNombreProfesor() + " "+item.getHorario().getApellidoProfesor() +"  Días: " + item.getHorario().getDiaSemana()  : "Sin horario" %></td>
	                    <td><%= item.getFechaMatricula() != null ? item.getFechaMatricula().toString() : "No registrada" %></td>
	                    <td><%= item.getEstadoMatricula() %></td>
	                    <td><%= item.getObservaciones() %></td>
	                    <td><%= item.getModoMatricula() %></td>
	                    <td><%= item.getCiclo() %></td>
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
