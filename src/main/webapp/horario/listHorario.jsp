<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Horarios</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                <li class="breadcrumb-item active">Horarios</li>
            </ol>
        </nav>
    </div>

    <section class="section dashboard">
        <div class="row">
            <table class="table datatable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Curso</th>
                        <th>Profesor</th>
                        <th>Sección</th>
                        <th>Día</th>
                        <th>Hora</th>
                        <th>Modalidad</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        @SuppressWarnings("unchecked")
                        List<Horario> listaHorarios = (List<Horario>) request.getAttribute("lista");
                        if (listaHorarios != null) {
                            for (Horario item : listaHorarios) {
                    %>
                    <tr>
                        <td><%= item.getHorarioID() %></td>
                        <td><%= item.getNombreCurso() %></td>
                        <td><%= item.getNombreProfesor() + " " + item.getApellidoProfesor() %></td>
                        <td><%= item.getNombreSeccion() %></td>
                        <td><%= item.getDiaSemana() %></td>
                        <td><%= item.getHoraInicioFin() %></td>
                        <td><%= item.getModalidad() %></td>
                        <td><%= item.getEstado() %></td>
                        <td>
                            <a href="HorarioServlet?tipo=info&id=<%= item.getHorarioID() %>">
                                <img alt="" src="image/ic_info.svg" width="15" height="15" title="Detalle">
                            </a>
                            <a href="HorarioServlet?tipo=modif&id=<%= item.getHorarioID() %>">
                                <img alt="" src="image/ic_edit.svg" width="15" height="15" title="Editar">
                            </a>
                            <a href="HorarioServlet?tipo=delete&id=<%= item.getHorarioID() %>">
                                <img alt="" src="image/ic_delete.svg" width="15" height="15" title="Eliminar">
                            </a>
                        </td>
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
