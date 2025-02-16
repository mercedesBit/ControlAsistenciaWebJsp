 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Estilos personalizados -->
    <style>
        body {
            background-color: #f4f7fa;
            font-family: 'Arial', sans-serif;
            color: #333;
        }

        .login-container {
            max-width: 400px;
            margin: 100px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #007bff;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .form-group input:focus {
            border-color: #007bff;
            outline: none;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-size: 14px;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Inicio de Sesión</h2>
        <!-- Formulario de login que envía datos al servlet LoginServlet -->
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="nombreUsuario">Nombre de usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required />
            </div>

            <div class="form-group">
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required />
            </div>

            <button type="submit" class="btn btn-primary">Iniciar sesión</button>
        </form>

       
    </div>

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>

