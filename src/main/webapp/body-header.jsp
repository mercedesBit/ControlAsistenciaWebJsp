<%@ page import="java.util.Objects"%>

<header id="header" class="header fixed-top d-flex align-items-center">

	<div class="d-flex align-items-center justify-content-between">
		<a href="<%=request.getContextPath()%>/index.jsp"
			class="logo d-flex align-items-center"> <img
			src="<%=request.getContextPath()%>/assets/img/logo.png" alt="">
			<span class="d-none d-lg-block">Control de Asistencia</span>
		</a> <i class="bi bi-list toggle-sidebar-btn"></i>
	</div>
	<div class="search-bar">
		<form class="search-form d-flex align-items-center" method="POST"
			action="#">
			<input type="text" name="query" placeholder="Search"
				title="Enter search keyword" class="form-control">
			<button type="submit" title="Search" class="btn btn-primary ms-2">Buscar</button>
		</form>
	</div>
	<nav class="header-nav ms-auto">
		<ul class="d-flex align-items-center">


			<li class="nav-item dropdown pe-3"><a
				class="nav-link nav-profile d-flex align-items-center pe-0" href="#"
				data-bs-toggle="dropdown"> <img
					src="<%=request.getContextPath()%>/assets/img/profile-img.jpg"
					alt="Profile" class="rounded-circle"> <span
					class="d-none d-md-block dropdown-toggle ps-2"><%=!Objects.isNull(session.getAttribute("nombreUsuario")) ? (String) session.getAttribute("nombreUsuario") : ""%></span>
			</a>

				<ul
					class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">


					<li><a class="dropdown-item d-flex align-items-center"
						href="Login.jsp"> <i class="bi bi-box-arrow-right"></i> <span>Cerrar
								Sesión</span>
					</a></li>

				</ul>
		</ul>
	</nav>
</header>
<aside id="sidebar" class="sidebar">
	<ul class="sidebar-nav" id="sidebar-nav">
		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#components-navAs" data-bs-toggle="collapse" href="#">
				<i class="bi bi-layout-text-window-reverse"></i><span>Asistencias</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="components-navAs" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				
			
				
				<%
				String rol = (String) session.getAttribute("rol");
				if (rol != null && (rol.equals("Administrador") || rol.equals("Profesor"))) {
				%>
				
						<li> 
				<a href="<%=request.getContextPath()%>/AsistenciaEstudianteServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Lista de Asistencias</span>
				</a>
				</li>
				
			
				<%
				}
				%>





			</ul> <%
 if (rol != null && rol.equals("Administrador")) {
 %> <a class="nav-link collapsed" data-bs-target="#components-navHs"
			data-bs-toggle="collapse" href="#"> <i
				class="bi bi-layout-text-window-reverse"></i><span>Horarios</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="components-navHs" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				<li><a
					href="<%=request.getContextPath()%>/HorarioServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Ver Horarios</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/horario/regHorario.jsp"> <i
						class="bi bi-circle"></i><span>Registrar Horarios</span>
				</a></li>
			</ul> <a class="nav-link collapsed" data-bs-target="#components-navMs"
			data-bs-toggle="collapse" href="#"> <i
				class="bi bi-layout-text-window-reverse"></i><span>Matricula</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="components-navMs" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				<li><a
					href="<%=request.getContextPath()%>/MatriculaServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Ver Matriculas</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/matricula/regMatricula.jsp">
						<i class="bi bi-circle"></i><span>Registrar Matricula</span>
				</a></li>
			</ul> <a class="nav-link collapsed" data-bs-target="#components-nav"
			data-bs-toggle="collapse" href="#"> <i
				class="bi bi-menu-button-wide"></i><span>Mantenimientos</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="components-nav" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				<li><a
					href="<%=request.getContextPath()%>/EstudianteServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Estudiante</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/ProfesorServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Profesor</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/CursoServlet?tipo=listar">
						<i class="bi bi-circle"></i><span>Curso</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/SeccionServlet?tipo=listar">
						<i class="bi bi-circle"></i><span>Sección</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/PadresTutoresServlet?tipo=list">
						<i class="bi bi-circle"></i><span>Padres/Tutores</span>
				</a></li>
			</ul> <a class="nav-link collapsed"
			data-bs-target="#components-navReporte" data-bs-toggle="collapse"
			href="#"> <i class="bi bi-card-list"></i><span>Reportes</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="components-navReporte" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				<li><a
					href="<%=request.getContextPath()%>/CursoReporteServlet?action=reporte">
						<i class="bi bi-circle"></i><span>Reporte Cursos</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/AsistenciaEstudianteServlet?tipo=reporte">
						<i class="bi bi-circle"></i><span>Reporte Asistencia</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/PadresTutoresServlet?tipo=viewreporte">
						<i class="bi bi-circle"></i><span>Reporte Padres/Tutores</span>
				</a></li>
			</ul> <%
 }
 %></li>
	</ul>
</aside>
