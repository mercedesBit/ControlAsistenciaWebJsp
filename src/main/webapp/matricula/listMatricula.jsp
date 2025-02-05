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
        <h1>Matriculas</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                <li class="breadcrumb-item active">Matriculas</li>
            </ol>
        </nav>
    </div>

    <section class="section dashboard">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/matricula/regMatricula.jsp" role="button">Crear Nuevo</a>
        <div class="row">
            <table class="table datatable">
                <thead>
                    <tr>
                        <th>Código de Matrícula</th>
                        <th>Estudiante</th>
                        <th>Horario</th>
                        <th>Fecha Matrícula</th>
                        <th>Estado Matrícula</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        @SuppressWarnings("unchecked")
                        List<Matricula> listaMatriculas = (List<Matricula>)request.getAttribute("listaMatriculas");
                        if(listaMatriculas != null){
                            for(Matricula item : listaMatriculas){
                    %>
                    <tr>
                        <td><%= item.getCodigoMatricula() %></td>
                        <td><%= item.getEstudiante().getNombres() + " " + item.getEstudiante().getApellidos() %></td>
                        <td>horario pendiente implementar></td>
                        <td><%= item.getFechaMatricula() != null ? item.getFechaMatricula().toString() : "" %></td>
                        <td><%= item.getEstadoMatricula() %></td>
                       
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
