<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ include file="../body-header.jsp"%>
<%@page import="entidades.Curso"%>

<main id="main" class="main">
	<div class="pagetitle">
		<h1>Registrar Curso</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Inicio</li>
			</ol>
		</nav>
	</div>

	<section class="section dashboard">
		<div class="row">

			<form action="CursoServlet" method="post">
				<input type="hidden" name="accion" value="registrar">

				<div class="form-group">
					<label for="codigoCurso">Código del Curso</label> <input
						type="text" class="form-control" id="codigoCurso"
						name="codigoCurso" required>
				</div>

				<div class="form-group">
					<label for="nombreCurso">Nombre del Curso</label> <input
						type="text" class="form-control" id="nombreCurso"
						name="nombreCurso" required>
				</div>

				<div class="form-group">
					<label for="descripcion">Descripción</label>
					<textarea class="form-control" id="descripcion" name="descripcion"
						required></textarea>
				</div>

				<div class="form-group">
					<label for="creditos">Créditos</label> <input type="number"
						class="form-control" id="creditos" name="creditos" required>
				</div>


				



  <div class="mb-3">
        <label for="grado" class="form-label">Ciclo:</label>
        <select name="ciclo" id="ciclo" class="form-select " required>
            <% 
                // Recuperamos la lista de ciclos del request
                @SuppressWarnings("unchecked")
                List<Curso> ciclos = (List<Curso>) request.getAttribute("ciclos");
                
                // Verificamos si la lista no es null y contiene elementos
                if (ciclos != null) {
                    for (Curso ciclo : ciclos) {
            %>
                <!-- Creamos las opciones del ComboBox -->
                <option value="<%= ciclo.getId_ciclo() %>">
                    <%= ciclo.getCiclo() %>
                </option>
            <% 
                    }
                }
            %>
        </select>
    </div>
    

				<div class="form-group">
					<label for="nivel">Nivel</label> <input type="text"
						class="form-control" id="nivel" name="nivel" required>
				</div>

				
		

			

				<div class="form-group">
					<label for="notas">Notas</label>
					<textarea class="form-control" id="notas" name="notas"></textarea>
				</div>
			
	

				<br />
				<div class="form-actions">
					<button type="submit" class="btn btn-danger">Registrar</button>
					<a href="CursoServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
				</div>
			</form>
		</div>
	</section>
</main>

<%@ include file="../footer.jsp"%>