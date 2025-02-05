<%@page import="entidades.Horario"%>
<%@page import="modelo.CursoModel"%>
<%@page import="modelo.ProfesorModel"%>
<%@page import="modelo.SeccionModel"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Profesor"%>
<%@page import="entidades.Seccion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Modificar Horario</h1>
    </div>

    <section class="section dashboard">
        <div class="row">
            <%
                Horario horario = (Horario) request.getAttribute("horarioData");
                CursoModel cursoModel = new CursoModel();
                ProfesorModel profesorModel = new ProfesorModel();
                SeccionModel seccionModel = new SeccionModel();
            %>

            <form action="HorarioServlet?tipo=edit" method="post">
                <input type="hidden" name="idHorario" value="<%=(horario != null) ? horario.getHorarioID() : "" %>">
                
                <div class="form-group">
                    <label>Curso</label>
                    <select name="txtCursoID" class="form-control">
                        <% for (Curso obj : cursoModel.listarTodos()) { %>
                        <option value="<%=obj.getCursoID()%>" <%= (horario != null && obj.getCursoID() == horario.getCursoID()) ? "selected" : "" %>>
                            <%=obj.getNombreCurso()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Profesor</label>
                    <select name="txtProfesorID" class="form-control">
                        <% for (Profesor obj : profesorModel.listProfesor()) { %>
                        <option value="<%=obj.getProfesorID()%>" <%= (horario != null && obj.getProfesorID() == horario.getProfesorID()) ? "selected" : "" %>>
                            <%=obj.getNombres() + " " + obj.getApellidos()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Sección</label>
                    <select name="txtSeccionID" class="form-control">
                        <% for (Seccion obj : seccionModel.listarTodos()) { %>
                        <option value="<%=obj.getSeccionID()%>" <%= (horario != null && obj.getSeccionID() == horario.getSeccionID()) ? "selected" : "" %>>
                            <%=obj.getNombreSeccion()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Día de la Semana</label>
                    <input class="form-control" type="text" name="txtDiaSemana" value="<%=(horario != null) ? horario.getDiaSemana() : ""%>">
                </div>

                <div class="form-group">
                    <label>Hora de Inicio y Fin</label>
                    <input class="form-control" type="text" name="txtHoraInicioFin" value="<%=(horario != null) ? horario.getHoraInicioFin() : ""%>">
                </div>

                <div class="form-group">
                    <label>Fecha de Inicio</label>
                    <input class="form-control" type="date" name="txtFechaInicio" value="<%=(horario != null && horario.getFechaInicio() != null) ? horario.getFechaInicio().toString() : ""%>">
                </div>

                <div class="form-group">
                    <label>Fecha de Fin</label>
                    <input class="form-control" type="date" name="txtFechaFin" value="<%=(horario != null && horario.getFechaFin() != null) ? horario.getFechaFin().toString() : ""%>">
                </div>

                <div class="form-group">
                    <label>Máximo de Estudiantes</label>
                    <input class="form-control" type="text" name="txtMaxEstudiantes" value="<%=(horario != null) ? horario.getMaxEstudiantes() : ""%>">
                </div>

                <div class="form-group">
                    <label>Modalidad</label>
                    <input class="form-control" type="text" name="txtModalidad" value="<%=(horario != null) ? horario.getModalidad() : ""%>">
                </div>

                <div class="form-group">
                    <label>Estado</label>
                    <input class="form-control" type="text" name="txtEstado" value="<%=(horario != null) ? horario.getEstado() : ""%>">
                </div>

                <br>
                <input class="btn btn-success" type="submit" value="Actualizar">
                <a class="btn btn-secondary" href="HorarioServlet?tipo=list">Regresar al Listado</a>
            </form>
        </div>
    </section>
</main>

<%@ include file="../footer.jsp"%>
