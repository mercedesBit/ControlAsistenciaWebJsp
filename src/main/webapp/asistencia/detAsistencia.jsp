<%@page import="java.util.List"%>
<%@page import="entidades.AsistenciaEstudiante"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Detalle de Asistencia</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">
			<%
			AsistenciaEstudiante AsistenciaEstudiante = (AsistenciaEstudiante) request.getAttribute("asistenciaEstudiante");
			%>

			<form action="AsistenciEstudianteServlet" method="post">


				<input type="hidden" name="idAsistencia"
					value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getAsistenciaID() : ""%>">


		<div class="form-group">
    <label class="text-secondary">Estudiante</label> 
    <input class="form-control" type="text" name="txtEstudiante"
        value="<%=(AsistenciaEstudiante != null) ? "ID : " + AsistenciaEstudiante.getEstudianteID()  + " - " + AsistenciaEstudiante.getEstudiante().getNombres() + ' ' + AsistenciaEstudiante.getEstudiante().getApellidos() : ""%>"
        readonly>
</div>


				<div class="form-group">
					<label class="text-secondary">Comentario</label> <input
						class="form-control" type="text" name="txtComentario"
						value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getComentario() : ""%>"
						readonly>
				</div>



<div class="form-group">
    <label class="text-secondary">Día de Asistencia</label> 
    <input class="form-control" type="text" name="txtDiaAsistencia"
        value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getDiaAsistencia() + " " +  AsistenciaEstudiante.getFechaDeClase() : ""%>"
        readonly>
</div>




				<div class="form-group">
					<label class="text-secondary">Usuario que Registro</label> <input
						class="form-control" type="text" name="txtUsuario"
						value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getUsuarioRegistro() : ""%>"
						readonly>
				</div>

		<div class="form-group">
					<label class="text-secondary">Estado</label> <input
						class="form-control" type="text" name="txtEstado"
						value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getEstadoAsistencia() : ""%>"
						readonly>
				</div>



		



	



	<div class="form-group">
					<label class="text-secondary">Curso</label> <input
						class="form-control" type="text" name="txtCurso"
						value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getCurso().getNombreCurso() : ""%>"
						readonly>
				</div>
				
				
				<div class="form-group">
    <label class="text-secondary">Horario</label> 
    <input class="form-control" type="text" name="txtHorario"
        value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getHorario().getDiaSemana() + ' ' + " de" + ' '  + AsistenciaEstudiante.getHorario().getHoraInicioFin() : ""%>"
        readonly>
</div>




				<div class="form-group">
					<label class="text-secondary">Fecha de Actualizacion</label> <input
						class="form-control" type="text" name="txtFechaActualizacion"
						value="<%=(AsistenciaEstudiante != null) ? AsistenciaEstudiante.getFechaActualizacion() : ""%>"
						readonly>
				</div>
		

   <a class="btn btn-primary"
       href="AsistenciaEstudianteServlet?tipo=listAsistenciaxAlumno&horarioID=<%=AsistenciaEstudiante.getHorarioID()%>&estudianteID=<%=AsistenciaEstudiante.getEstudianteID()%>">
       Regresar
    </a>
			</form>
	

		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>

