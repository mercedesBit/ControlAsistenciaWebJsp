<%@page import="modelo.CursoModel"%>
<%@page import="modelo.ProfesorModel"%>
<%@page import="modelo.SeccionModel"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Profesor"%>
<%@page import="entidades.Seccion"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Registrar Horario de Curso</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
                <li class="breadcrumb-item active">Registrar Horario</li>
            </ol>
        </nav>
    </div>
    
    <section class="section dashboard">
        <div class="row">
            <form action="../HorarioServlet?tipo=regist" method="post">
                <div class="form-group">
                    <label>Curso</label>
                    <select name="txtCursoID" class="form-control">
                        <option value="">Selecciona un curso</option>
                        <% CursoModel modelC = new CursoModel();
                           List<Curso> listaC = modelC.listarTodos();
                           for (Curso obj : listaC) { %>
                        <option value="<%=obj.getCursoID()%>"><%=obj.getNombreCurso()%></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Profesor</label>
                    <select name="txtProfesorID" class="form-control">
                        <option value="">Selecciona un profesor</option>
                        <% ProfesorModel modelP = new ProfesorModel();
                           List<Profesor> listaP = modelP.listProfesor();
                           for (Profesor obj : listaP) { %>
                        <option value="<%=obj.getProfesorID()%>"><%=obj.getNombres() + " " + obj.getApellidos()%></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Sección</label>
                    <select name="txtSeccionID" class="form-control">
                        <option value="">Selecciona una sección</option>
                        <% SeccionModel modelS = new SeccionModel();
                           List<Seccion> listaS = modelS.listarTodos();
                           for (Seccion obj : listaS) { %>
                        <option value="<%=obj.getSeccionID()%>"><%=obj.getNombreSeccion()%></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Día de la Semana</label>
                    <input class="form-control" type="text" name="txtDiaSemana">
                </div>

                <div class="form-group">
                    <label>Hora Inicio - Fin</label>
                    <input class="form-control" type="text" name="txtHoraInicioFin">
                </div>

                <div class="form-group">
                    <label>Fecha de Inicio</label>
                    <input class="form-control" type="date" name="txtFechaInicio">
                </div>

                <div class="form-group">
                    <label>Fecha de Fin</label>
                    <input class="form-control" type="date" name="txtFechaFin">
                </div>

                <div class="form-group">
                    <label>Máximo de Estudiantes</label>
                    <input class="form-control" type="number" name="txtMaxEstudiantes">
                </div>

                <div class="form-group">
				    <label>Modalidad</label>
				    <select name="txtModalidad" class="form-control">
				        <option value="">Selecciona una modalidad</option>
				        <option value="Presencial">Presencial</option>
				        <option value="Virtual">Virtual</option>
				        <option value="Semipresencial">Semipresencial</option>
				    </select>
				</div>
				
				<div class="form-group">
				    <label>Estado</label>
				    <select name="txtEstado" class="form-control">
				        <option value="">Selecciona un estado</option>
				        <option value="Activo">Activo</option>
				        <option value="Inactivo">Inactivo</option>
				    </select>
				</div>

                <br>
                <div class="button">
                    <input type="submit" class="btn btn-danger" value="Registrar">
                    <a href="../HorarioServlet?tipo=list" role="button" class="btn btn-secondary">Listar</a>
                </div>
            </form>
        </div>
    </section>
</main>

<%@ include file="../footer.jsp"%>