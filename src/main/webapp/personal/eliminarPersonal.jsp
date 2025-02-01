<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Entidades.PersonalAdministrativoModel" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    PersonalAdministrativoModel model = new PersonalAdministrativoModel();
    int resultado = model.eliminar(id);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eliminar Personal Administrativo</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #E0F7FA;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #0277BD;
            padding: 20px;
            background-color: #B3E5FC;
            color: white;
            margin: 0;
            font-size: 24px;
            border-bottom: 3px solid #0277BD;
        }
        .container {
            width: 90%;
            max-width: 800px;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        p {
            font-size: 18px;
            color: #555;
            text-align: center;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
            color: #0288D1;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Eliminar Personal Administrativo</h1>
    <div class="container">
        <%
            if (resultado > 0) {
        %>
            <p>El registro fue eliminado exitosamente.</p>
        <%
            } else {
        %>
            <p>Error al eliminar el registro.</p>
        <%
            }
        %>
        <a href="listarPersonal.jsp">Volver al listado</a>
    </div>
</body>
</html>
