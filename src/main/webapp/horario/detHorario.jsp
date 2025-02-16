<%@page import="entidades.Horario"%>
<%@page import="modelo.CursoModel"%>
<%@page import="modelo.ProfesorModel"%>
<%@page import="modelo.SeccionModel"%>
<%@page import="entidades.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Detalle de Horario</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                <li class="breadcrumb-item active">Detalle Horario</li>
            </ol>
        </nav>
    </div>

    <section class="section dashboard">
        <div class="row">
            <%
                Horario horario = (Horario) request.getAttribute("horarioData");
  
            %>

            <form>
                <input type="hidden" name="idHorario" value="<%=(horario != null) ? horario.getHorarioID() : "" %>">

                <div class="form-group">
                    <label class="text-secondary">Curso</label>
                    <select id="txtCursoID" name="txtCursoID" class="form-control" disabled>
                        <option value="">Selecciona una opción</option>
                        <%
                        int selectedCursoID = (horario != null) ? horario.getCurso().getCursoID() : 0;
                        
                        CursoModel modelC = new CursoModel();
                        List<Curso> listaC = modelC.listarTodos();
                        for (Curso obj : listaC) {
                            int cursoID = obj.getCursoID();
                        %>
                        <option value="<%=cursoID%>"
                            <%=cursoID == selectedCursoID ? "selected" : ""%>>
                            <%=obj.getNombreCurso()%>
                        </option>
                        <%
                        }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Profesor</label>
                    <select id="txtProfesorID" name="txtProfesorID" class="form-control" disabled>
                        <option value="">Selecciona una opción</option>
                        <%
                        int selectedProfesorID = (horario != null) ? horario.getProfesor().getProfesorID() : 0;
                        
                        ProfesorModel modelP = new ProfesorModel();
                        List<Profesor> listaP = modelP.listProfesor();
                        for (Profesor obj : listaP) {
                            int profesorID = obj.getProfesorID();
                        %>
                        <option value="<%=profesorID%>"
                            <%=profesorID == selectedProfesorID ? "selected" : ""%>>
                            <%=obj.getNombres() + " " + obj.getApellidos()%>
                        </option>
                        <%
                        }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Sección</label>
                    <select id="txtSeccionID" name="txtSeccionID" class="form-control" disabled>
                        <option value="">Selecciona una opción</option>
                        <%
                        int selectedSeccionID = (horario != null) ? horario.getSeccion().getSeccionID() : 0;
                        
                        SeccionModel modelS = new SeccionModel();
                        List<Seccion> listaS = modelS.listarTodos();
                        for (Seccion obj : listaS) {
                            int seccionID = obj.getSeccionID();
                        %>
                        <option value="<%=seccionID%>"
                            <%=seccionID == selectedSeccionID ? "selected" : ""%>>
                            <%=obj.getNombreSeccion()%>
                        </option>
                        <%
                        }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Día de la Semana</label>
                    <input class="form-control" type="text" value="<%=(horario != null) ? horario.getDiaSemana() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Hora de Inicio y Fin</label>
                    <input class="form-control" type="text" value="<%=(horario != null) ? horario.getHoraInicioFin() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Fecha de Inicio</label>
                    <input class="form-control" type="date" value="<%=(horario != null && horario.getFechaInicio() != null) ? horario.getFechaInicio().toString() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Fecha de Fin</label>
                    <input class="form-control" type="date" value="<%=(horario != null && horario.getFechaFin() != null) ? horario.getFechaFin().toString() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Máximo de Estudiantes</label>
                    <input class="form-control" type="text" value="<%=(horario != null) ? horario.getMaxEstudiantes() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Modalidad</label>
                    <input class="form-control" type="text" value="<%=(horario != null) ? horario.getModalidad() : ""%>" readonly>
                </div>

                <div class="form-group">
                    <label class="text-secondary">Estado</label>
                    <input class="form-control" type="text" value="<%=(horario != null) ? horario.getEstado() : ""%>" readonly>
                </div>

                <br>
                <a class="btn btn-primary" href="HorarioServlet?tipo=list" role="button">Regresar al Listado</a>
            </form>

        </div>
    </section>
</main>

<%@ include file="../footer.jsp"%>

