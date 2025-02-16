<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../head.jsp" %>
<%@ include file="../body-header.jsp" %>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Control de Asistencia</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
                <li class="breadcrumb-item active">Bienvenido</li>
            </ol>
        </nav>
    </div>

    <section class="section dashboard">
        <div class="row">
            <!-- Tarjeta de bienvenida -->
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">¡Bienvenido al Sistema de Control de Asistencia!</h5>
                        
                        <p>
                            Hola, 
                            <strong>
                                <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "Usuario" %>
                            </strong> 👋. 
                            Este sistema te permite gestionar la asistencia de estudiantes y profesores de manera eficiente.
                        </p>

                        <p>
                            Usa el menú lateral para acceder a las diferentes secciones del sistema. Aquí tienes una guía rápida sobre cómo utilizarlo:
                        </p>

                        <ul>
                            <li><strong>📌 Asistencias:</strong> Consulta y gestiona la asistencia de los estudiantes.</li>
                            <li><strong>🕒 Horarios:</strong> Visualiza y administra los horarios de clases.</li>
                            <li><strong>📝 Matrícula:</strong> Gestiona el registro de matrículas de los estudiantes.</li>
                            <li><strong>⚙️ Mantenimientos:</strong> Administra información de estudiantes, profesores, cursos y secciones.</li>
                            <li><strong>📊 Reportes:</strong> Genera informes de asistencia, cursos y padres/tutores.</li>
                            <li><strong>🔒 Cerrar Sesión:</strong> Finaliza tu sesión de manera segura.</li>
                        </ul>

                        <p>Si necesitas ayuda, contacta al administrador del sistema.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<%@ include file="../footer.jsp" %>