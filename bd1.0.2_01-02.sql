create database bd_control_asistencia_alumnadoPRUEBA;
use bd_control_asistencia_alumnadoPRUEBA;


SHOW VARIABLES LIKE 'lc_time_names';
SET SESSION lc_time_names = 'es_ES';
SET lc_time_names = 'es_ES';

-- Crear tabla Roles
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    NombreRole VARCHAR(50) NOT NULL
);

-- Crear tabla Usuarios
CREATE TABLE Usuarios (
    UsuarioID INT PRIMARY KEY AUTO_INCREMENT,
    NombreUsuario VARCHAR(50) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL,
    RoleID INT NOT NULL,
    FechaRegistro DATE,
    Estado VARCHAR(20),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Crear tabla Seccion
CREATE TABLE Seccion (
    SeccionID INT AUTO_INCREMENT PRIMARY KEY,
    NombreSeccion VARCHAR(50),
    NumeroAula VARCHAR(20),
    Estado VARCHAR(20),
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE
);

-- Crear tabla PadresTutores
CREATE TABLE PadresTutores (
    TutorID INT AUTO_INCREMENT PRIMARY KEY,
    TipoDocumento VARCHAR(10),
    NumeroDocumento VARCHAR(16),
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    CorreoElectronico VARCHAR(100),
    TelefonoMovil VARCHAR(15),
    TelefonoCasa VARCHAR(15),
    Direccion VARCHAR(200),
    RelacionEstudiante VARCHAR(50),
    FechaNacimiento DATE,
    Ocupacion VARCHAR(100),
    Estado VARCHAR(20),
    RedesSociales TEXT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE
);

-- Crear tabla Estudiante
CREATE TABLE Estudiante (
    EstudianteID INT AUTO_INCREMENT PRIMARY KEY,
    TipoDocumento VARCHAR(10),
    NumeroDocumento VARCHAR(16),
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    FechaNacimiento DATE,
    Genero VARCHAR(10),
    Email VARCHAR(100),
    Telefono VARCHAR(15),
    Direccion VARCHAR(200),
    EstadoCivil VARCHAR(20),
    FechaIngreso DATE,
    Estado VARCHAR(20),
    RedesSociales TEXT,
    EmergenciaContacto VARCHAR(200),
    TutorID INT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE,
    FOREIGN KEY (TutorID) REFERENCES PadresTutores(TutorID)
);

-- Crear tabla Profesor
CREATE TABLE Profesor (
    ProfesorID INT AUTO_INCREMENT PRIMARY KEY,
    TipoDocumento VARCHAR(10),
    NumeroDocumento VARCHAR(16),
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    FechaNacimiento DATE,
    CorreoInstitucional VARCHAR(100),
    TelefonoMovil VARCHAR(15),
    TelefonoTrabajo VARCHAR(15),
    FechaContratacion DATE,
    Especialidad VARCHAR(100),
    GradoAcademico VARCHAR(50),
    HorasDocencia INT,
    Estado VARCHAR(20),
    Direccion VARCHAR(200),
    ModalidadTrabajo VARCHAR(50),
    RedesSociales TEXT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE
);

-- Crear tabla Ciclo
CREATE TABLE Ciclo (
    id_ciclo INT PRIMARY KEY,  -- ID del ciclo (Clave primaria)
    nombre_ciclo VARCHAR(50)not null  
);

-- Crear tabla Curso
CREATE TABLE Curso (
    CursoID INT AUTO_INCREMENT PRIMARY KEY,
    CodigoCurso VARCHAR(20),
    NombreCurso VARCHAR(100),
    Descripcion VARCHAR(255),
    Creditos INT,
    Ciclo INT,  -- Cambiamos Ciclo a CicloID para que sea una clave foránea
    Nivel VARCHAR(50),
    Estado VARCHAR(20),
    Notas TEXT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE,
    FOREIGN KEY (Ciclo) REFERENCES Ciclo(id_ciclo)  -- Referencia a la tabla Ciclo
);

-- Modificar, colocar id de horario,
-- Crear tabla Asistencia
CREATE TABLE Asistencia (
    AsistenciaID INT AUTO_INCREMENT PRIMARY KEY,
    EstudianteID INT,
    CursoID INT,
    HoraAsistencia TIMESTAMP,
    Estado VARCHAR(20),
    Comentario TEXT null,
    TipoAsistencia VARCHAR(50),
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE null,
    FOREIGN KEY (EstudianteID) REFERENCES Estudiante(EstudianteID),
    FOREIGN KEY (CursoID) REFERENCES Curso(CursoID)
);

-- Crear tabla Horario
CREATE TABLE Horario (
    HorarioID INT AUTO_INCREMENT PRIMARY KEY,
    CursoID INT,
    ProfesorID INT,
    SeccionID INT,
    DiaSemana VARCHAR(100),
    HoraInicioFin VARCHAR(200),
    FechaInicio DATE NOT NULL,  -- Define la fecha de inicio del horario
    FechaFin DATE NOT NULL,     -- Define la fecha de fin del horario
    MaxEstudiantes INT,
    Modalidad ENUM('Presencial', 'Virtual', 'Semipresencial') NOT NULL, -- Modalidad del horario
    Estado ENUM('Activo', 'Inactivo') NOT NULL, -- Estado del horario
    FechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de registro
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Última actualización
    FOREIGN KEY (CursoID) REFERENCES Curso(CursoID),
    FOREIGN KEY (ProfesorID) REFERENCES Profesor(ProfesorID),
    FOREIGN KEY (SeccionID) REFERENCES Seccion(SeccionID)
);

  

    
-- Crear tabla Horario_Estudiante
CREATE TABLE Horario_Estudiante (
    EstudianteID INT,                               -- Referencia al Estudiante
    HorarioID INT,     
    codigo_matricula VARCHAR(50),
       fecha_matricula DATE,
    estado_matricula VARCHAR(20),
    observaciones VARCHAR(100),         
    modo_matricula VARCHAR(30), 
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    -- Referencia al Horario
    PRIMARY KEY (EstudianteID, HorarioID),          -- Clave primaria compuesta por EstudianteID y HorarioID
    FOREIGN KEY (EstudianteID) REFERENCES Estudiante(EstudianteID) ON DELETE CASCADE ON UPDATE CASCADE, -- Relaciona con la tabla Estudiante
    FOREIGN KEY (HorarioID) REFERENCES Horario(HorarioID)ON DELETE CASCADE ON UPDATE CASCADE           -- Relaciona con la tabla Horario
);






DELIMITER //
CREATE TRIGGER llenar_nombres_apellidos BEFORE INSERT ON Horario_Estudiante
FOR EACH ROW
BEGIN
    DECLARE nombre_estudiante VARCHAR(100);
    DECLARE apellido_estudiante VARCHAR(100);

    -- Obtener los nombres y apellidos del estudiante
    SELECT Nombres, Apellidos INTO nombre_estudiante, apellido_estudiante
    FROM Estudiante WHERE EstudianteID = NEW.EstudianteID;

    -- Asignar los valores al nuevo registro
    SET NEW.Nombres = nombre_estudiante;
    SET NEW.Apellidos = apellido_estudiante;
END;
//
DELIMITER ;




-- Crear tabla ASISTENCIA_ESTUDIANTE
CREATE TABLE ASISTENCIA_ESTUDIANTE (
    AsistenciaID INT PRIMARY KEY AUTO_INCREMENT,
    EstudianteID INT,                                 -- Referencia al Estudiante
    HorarioID INT,                                   -- Referencia al Horario
    EstadoAsistencia ENUM('Asistencia', 'Inasistencia','En espera', 'Inasistencia Justificada') NOT NULL, 
    Comentario TEXT NULL,
    FECHADECLASE DATE NOT NULL,
    DIAASISTENCIA VARCHAR(15) GENERATED ALWAYS AS (DAYNAME(FECHADECLASE)) STORED,
    UsuarioRegistro VARCHAR(20),
    FECHAACTUALIZACION DATE,
    FOREIGN KEY (EstudianteID, HorarioID) REFERENCES Horario_Estudiante(EstudianteID, HorarioID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);


 
    
CREATE TABLE Matricula (
    codigo_matricula VARCHAR(50) UNIQUE,  
    id_estudiante INT,
    id_horario INT,
    fecha_matricula DATE,
    estado_matricula VARCHAR(20),
    observaciones VARCHAR(100),         
    modo_matricula VARCHAR(30), 
    ciclo VARCHAR(30),          
    PRIMARY KEY (id_estudiante, id_horario),
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(EstudianteID),
    FOREIGN KEY (id_horario) REFERENCES Horario(HorarioID)
);



DELIMITER //

CREATE TRIGGER trg_InsertHorarioEstudiante
AFTER INSERT ON Matricula
FOR EACH ROW
BEGIN
    INSERT INTO Horario_Estudiante (
        EstudianteID, HorarioID, Nombres, Apellidos, codigo_matricula, 
        fecha_matricula, estado_matricula, observaciones, modo_matricula
    )
    SELECT 
        NEW.id_estudiante, 
        NEW.id_horario, 
        e.Nombres, 
        e.Apellidos, 
        NEW.codigo_matricula, 
        NEW.fecha_matricula, 
        NEW.estado_matricula, 
        NEW.observaciones, 
        NEW.modo_matricula
    FROM Estudiante e
    WHERE e.EstudianteID = NEW.id_estudiante;
END;

//

DELIMITER ;
/*** PROCEDIMIENTOS ALMACENADOS DE USUARIO ***/
-- Procedimiento para obtener la asistencia
DELIMITER $$
CREATE PROCEDURE usp_obtener_asistencia(
    IN p_CursoID INT,
    IN p_FechaInicio DATE,
    IN p_FechaFin DATE
)
BEGIN
    SELECT 
        A.AsistenciaID,
        A.EstudianteID,
        A.CursoID,
        C.NombreCurso,
        E.Nombres,
        E.Apellidos,
        A.PersonalID,
        A.HoraAsistencia,
        A.Estado,
        A.Comentario,
        A.TipoAsistencia,
        A.FechaRegistro,
        A.UsuarioRegistro,
        A.FechaActualizacion
    FROM 
        Asistencia A
   JOIN Curso C
   ON A.CursoID = C.CursoID
   JOIN Estudiante E
   ON A.EstudianteID = E.EstudianteID
    WHERE 
		A.CursoID = p_CursoID
        AND A.FechaRegistro BETWEEN p_FechaInicio AND p_FechaFin;
END $$
DELIMITER ;

-- Procedimiento para obtener la asistencia por tipo y fecha de asistencia
DELIMITER $$
CREATE PROCEDURE usp_obtener_asistencia_por_tipo_fecha(
    IN p_Tipo_Asistencia VARCHAR(20),
    IN p_Fecha DATE
)
BEGIN
    SELECT 
        A.AsistenciaID,
        A.EstudianteID,
        A.CursoID,
        C.NombreCurso,
        E.Nombres,
        E.Apellidos,
        A.PersonalID,
        A.HoraAsistencia,
        A.Estado,
        A.Comentario,
        A.TipoAsistencia,
        A.FechaRegistro,
        A.UsuarioRegistro,
        A.FechaActualizacion
    FROM 
        Asistencia A
    JOIN Curso C ON A.CursoID = C.CursoID
    JOIN Estudiante E ON A.EstudianteID = E.EstudianteID
    WHERE 
        A.TipoAsistencia = p_Tipo_Asistencia
        AND A.FechaRegistro = p_Fecha;
END $$
DELIMITER ;

-- Trigger para validar el maximo de estudiantes en un horario
DELIMITER $$
CREATE TRIGGER ValidarMaxEstudiantes
BEFORE INSERT ON Horario_Estudiante
FOR EACH ROW
BEGIN
    DECLARE max_estudiantes INT;
    
    -- Obtener la cantidad máxima de estudiantes para el horario
    SELECT MaxEstudiantes INTO max_estudiantes
    FROM Horario
    WHERE HorarioID = NEW.HorarioID;
    
    -- Contar la cantidad de estudiantes ya registrados para el horario
    IF (SELECT COUNT(*) FROM Horario_Estudiante WHERE HorarioID = NEW.HorarioID) >= max_estudiantes THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Se ha alcanzado la cantidad máxima de estudiantes para este horario';
    END IF;
END $$
DELIMITER ;





DELIMITER $$

-- Trigger para validar fecha al insertar
CREATE TRIGGER ValidarFechaClase_Insert
BEFORE INSERT ON ASISTENCIA_ESTUDIANTE
FOR EACH ROW
BEGIN
    DECLARE fecha_inicio DATE;
    DECLARE fecha_fin DATE;

    -- Obtiene las fechas de inicio y fin del horario
    SELECT FechaInicio, FechaFin INTO fecha_inicio, fecha_fin
    FROM Horario
    WHERE HorarioID = NEW.HorarioID;

    -- Verifica que la fecha de clase esté dentro del rango
    IF NEW.FECHADECLASE < fecha_inicio OR NEW.FECHADECLASE > fecha_fin THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: La fecha de la clase está fuera del rango permitido.';
    END IF;
END $$

-- Trigger para validar fecha al actualizar
CREATE TRIGGER ValidarFechaClase_Update
BEFORE UPDATE ON ASISTENCIA_ESTUDIANTE
FOR EACH ROW
BEGIN
    DECLARE fecha_inicio DATE;
    DECLARE fecha_fin DATE;

    -- Obtiene las fechas de inicio y fin del horario
    SELECT FechaInicio, FechaFin INTO fecha_inicio, fecha_fin
    FROM Horario
    WHERE HorarioID = NEW.HorarioID;

    -- Verifica que la fecha de clase esté dentro del rango
    IF NEW.FECHADECLASE < fecha_inicio OR NEW.FECHADECLASE > fecha_fin THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se puede actualizar a una fecha fuera del rango permitido.';
    END IF;
END $$

DELIMITER ;




/*** INSERCIONES INICIALES PARA LA BASE DE DATOS ***/
-- Insertar datos en la tabla Roles
INSERT INTO Roles (NombreRole)
VALUES ('Administrador'), ('Profesor'), ('Estudiante'), ('Personal Administrativo');

-- Insertar datos en la tabla Usuarios
INSERT INTO Usuarios (NombreUsuario, Contrasena, RoleID, FechaRegistro, Estado)
VALUES ('admin', 'password123', 1, CURDATE(), 'Activo');

-- Insertar datos en la tabla Seccion
INSERT INTO Seccion (NombreSeccion, NumeroAula, Estado, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('Sección A', 'A-101', 'Activo', CURDATE(), 'admin', CURDATE()),
('Sección B', 'B-202', 'Inactivo', CURDATE(), 'admin', CURDATE());

-- Insertar datos en la tabla PadresTutores
INSERT INTO PadresTutores (
    TipoDocumento, NumeroDocumento, Nombres, Apellidos, CorreoElectronico, 
    TelefonoMovil, TelefonoCasa, Direccion, RelacionEstudiante, FechaNacimiento, 
    Ocupacion, Estado, RedesSociales, FechaRegistro, UsuarioRegistro, FechaActualizacion
) VALUES 
('DNI', '87654321', 'María', 'López', 'maria.lopez@mail.com', '987123456', '012345678', 'Calle 5, Zona A', 'Madre', '1982-08-15', 'Doctora', 'Activo', 'Instagram: maria.lopez', CURDATE(), 'admin', CURDATE()),
('DNI', '56789012', 'José', 'Fernández', 'jose.fernandez@mail.com', '984567890', '012345679', 'Av. Central 456', 'Padre', '1975-03-20', 'Abogado', 'Activo', 'LinkedIn: jose.fernandez', CURDATE(), 'admin', CURDATE()),
('DNI', '23456789', 'Ana', 'González', 'ana.gonzalez@mail.com', '951234567', '012345680', 'Jr. Primavera 789', 'Madre', '1985-12-05', 'Profesora', 'Activo', 'Facebook: ana.gonzalez', CURDATE(), 'admin', CURDATE()),
('DNI', '34567890', 'Pedro', 'Rojas', 'pedro.rojas@mail.com', '987987654', '012345681', 'Pasaje Las Flores 321', 'Padre', '1978-07-22', 'Empresario', 'Activo', 'Twitter: pedro.rojas', CURDATE(), 'admin', CURDATE()),
('DNI', '45678901', 'Elena', 'Martínez', 'elena.martinez@mail.com', '963852741', '012345682', 'Urbanización San Juan', 'Madre', '1983-11-30', 'Contadora', 'Activo', 'Instagram: elena.martinez', CURDATE(), 'admin', CURDATE()),
('DNI', '56789023', 'Luis', 'Castro', 'luis.castro@mail.com', '987321654', '012345683', 'Barrio Nuevo 456', 'Padre', '1970-09-10', 'Arquitecto', 'Activo', 'LinkedIn: luis.castro', CURDATE(), 'admin', CURDATE()),
('DNI', '67890123', 'Patricia', 'Vega', 'patricia.vega@mail.com', '912345678', '012345684', 'Villa Sur 789', 'Madre', '1987-04-18', 'Psicóloga', 'Activo', 'Facebook: patricia.vega', CURDATE(), 'admin', CURDATE()),
('DNI', '78901234', 'Fernando', 'Hidalgo', 'fernando.hidalgo@mail.com', '945612378', '012345685', 'Los Álamos 123', 'Padre', '1973-06-25', 'Médico', 'Activo', 'Instagram: fernando.hidalgo', CURDATE(), 'admin', CURDATE()),
('DNI', '89012345', 'Gabriela', 'Ramírez', 'gabriela.ramirez@mail.com', '963258741', '012345686', 'San Miguel 456', 'Madre', '1980-02-14', 'Administradora', 'Activo', 'Twitter: gabriela.ramirez', CURDATE(), 'admin', CURDATE());

-- Insertar datos en la tabla Estudiante
INSERT INTO Estudiante (
    TipoDocumento, NumeroDocumento, Nombres, Apellidos, FechaNacimiento, 
    Genero, Email, Telefono, Direccion, EstadoCivil, FechaIngreso, 
    Estado, RedesSociales, EmergenciaContacto, TutorID, FechaRegistro, 
    UsuarioRegistro, FechaActualizacion
) VALUES 
('DNI', '10000001', 'Juan', 'Pérez', '2005-04-15', 'M', 'juan.perez@mail.com', '987654321', 'Av. Siempre Viva 742', 'Soltero', '2023-03-10', 'Activo', 'Facebook: juan.perez', '987654321', 1, CURDATE(), 'admin', CURDATE()),
('DNI', '10000002', 'María', 'Gómez', '2006-06-20', 'F', 'maria.gomez@mail.com', '987654322', 'Jr. Los Olivos 456', 'Soltera', '2023-03-12', 'Activo', 'Instagram: maria.gomez', '987654322', 2, CURDATE(), 'admin', CURDATE()),
('DNI', '10000003', 'Carlos', 'Martínez', '2007-08-10', 'M', 'carlos.martinez@mail.com', '987654323', 'Calle Ficticia 123', 'Casado', '2023-03-15', 'Activo', 'Twitter: carlos.martinez', '987654323', 3, CURDATE(), 'admin', CURDATE()),
('DNI', '10000004', 'Ana', 'Ramírez', '2005-02-05', 'F', 'ana.ramirez@mail.com', '987654324', 'Av. Libertador 789', 'Soltera', '2023-03-18', 'Activo', 'LinkedIn: ana.ramirez', '987654324', 4, CURDATE(), 'admin', CURDATE()),
('DNI', '10000005', 'Luis', 'Sánchez', '2006-11-12', 'M', 'luis.sanchez@mail.com', '987654325', 'Callejón de la Paz 456', 'Divorciado', '2023-03-20', 'Activo', 'Facebook: luis.sanchez', '987654325', 5, CURDATE(), 'admin', CURDATE()),
('DNI', '10000006', 'Patricia', 'Fernández', '2004-07-28', 'F', 'patricia.fernandez@mail.com', '987654326', 'Paseo de la Reforma 321', 'Viuda', '2023-03-22', 'Activo', 'Instagram: patricia.fernandez', '987654326', 6, CURDATE(), 'admin', CURDATE()),
('DNI', '10000007', 'Miguel', 'Díaz', '2005-01-30', 'M', 'miguel.diaz@mail.com', '987654327', 'Calle Nueva 876', 'Casado', '2023-03-25', 'Activo', 'Twitter: miguel.diaz', '987654327', 7, CURDATE(), 'admin', CURDATE()),
('DNI', '10000008', 'Lucía', 'Torres', '2006-03-22', 'F', 'lucia.torres@mail.com', '987654328', 'Jr. El Sol 234', 'Soltera', '2023-03-28', 'Activo', 'LinkedIn: lucia.torres', '987654328', 8, CURDATE(), 'admin', CURDATE()),
('DNI', '10000009', 'Andrés', 'Vega', '2005-05-19', 'M', 'andres.vega@mail.com', '987654329', 'Av. Gran Ciudad 102', 'Soltero', '2023-03-30', 'Activo', 'Facebook: andres.vega', '987654329', 9, CURDATE(), 'admin', CURDATE());

-- Insertar datos en la tabla Profesor
INSERT INTO Profesor (TipoDocumento, NumeroDocumento, Nombres, Apellidos, FechaNacimiento, CorreoInstitucional, TelefonoMovil, TelefonoTrabajo, FechaContratacion, Especialidad, GradoAcademico, HorasDocencia, Estado, Direccion, ModalidadTrabajo, RedesSociales, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('DNI', '11223344', 'Luis', 'Gomez', '1985-07-20', 'luis.gomez@instituto.edu', '987654324', '022345678', '2015-02-15', 'Matemáticas', 'Licenciado', 20, 'Activo', 'Av. Universitaria 789', 'Presencial', 'Twitter: @luisgomez', CURDATE(), 'admin', CURDATE());

-- Insertar datos en la tabla Ciclo
INSERT INTO Ciclo (id_ciclo, nombre_ciclo)
VALUES 
(1, 'Primer ciclo'),
(2, 'Segundo ciclo '),
(3,  'Tercer ciclo' ),
(4,  'Cuarto ciclo'),
(5, 'Quinto ciclo'),
(6, 'Sexto ciclo ');

-- Insertar daStos en la tabla Curso
INSERT INTO Curso (CodigoCurso, NombreCurso, Descripcion, Creditos, Ciclo, Nivel, Estado, Notas, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('CS101', 'Introduccion a la Programacion', 'Curso de introducción a la programación en Java', 5, 1, 'Básico', 'Activo', 'Ninguno', '2025-03-01', 'admin', '2025-03-01');


-- Insertar datos en la tabla Horario
INSERT INTO Horario (CursoID, ProfesorID, SeccionID, DiaSemana, HoraInicioFin, FechaInicio, FechaFin, MaxEstudiantes, Modalidad, Estado, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES 
(1, 1, 1, 'Lunes, Miércoles y Viernes', '08:00 - 10:00', '2025-03-01', '2025-03-30', 30, 'Presencial', 'Activo', CURDATE(), 'admin', CURDATE()),
(1, 1, 1, 'Martes y Jueves', '10:00 - 12:00', '2025-03-01', '2025-03-30', 30, 'Semipresencial', 'Activo', CURDATE(), 'admin', CURDATE());


-- Insertar datos en la tabla Matricula
INSERT INTO Matricula (Codigo_Matricula, id_estudiante, id_horario, Fecha_Matricula, Estado_Matricula, Observaciones, Modo_Matricula, Ciclo) 
VALUES ('MAT123', 1, 1, '2024-02-01', 'Activo', 'Sin observaciones', 'Online', 'Primer Ciclo');


-- Insertar datos en la tabla ASISTENCIA_ESTUDIANTE
INSERT INTO ASISTENCIA_ESTUDIANTE (EstudianteID, HorarioID, EstadoAsistencia, Comentario, FECHADECLASE, UsuarioRegistro)
VALUES 
(1, 1, 'Asistencia', 'Clase introductoria - sesión 1', '2025-03-01', 'admin'),
(1, 1, 'Inasistencia Justificada', 'Problemas de salud', '2025-03-02', 'admin');

-- Primero, verificamos si el número de estudiantes actuales en el horario es menor que la capacidad máxima:
SELECT COUNT(*) AS CantidadEstudiantes
FROM Horario_Estudiante
WHERE HorarioID = 2;  -- Reemplaza 1 con el HorarioID específico

SELECT h.HorarioID, c.NombreCurso, p.Nombres AS NombreProfesor, p.Apellidos AS ApellidoProfesor, s.NombreSeccion, h.DiaSemana, h.HoraInicioFin, h.Modalidad, h.Estado
FROM Horario h
JOIN Curso c ON h.CursoID = c.CursoID
JOIN Profesor p ON h.ProfesorID = p.ProfesorID
JOIN Seccion s ON h.SeccionID = s.SeccionID;





-- PROCEDIMIENTO PARA OBTENER ASISTENCIA CON ID
DELIMITER $$
CREATE PROCEDURE ObtenerAsistenciaxID (IN p_AsistenciaID INT)
BEGIN
    SELECT 
        ae.AsistenciaID, 
        ae.EstudianteID,
        e.Nombres,
        e.Apellidos,
        ae.HorarioID,
        c.NombreCurso,
        h.DiaSemana,
        h.HoraInicioFin,
        ae.Comentario,
        ae.EstadoAsistencia,
        ae.FECHADECLASE,
        ae.DIAASISTENCIA,
        ae.UsuarioRegistro,
        ae.FECHAACTUALIZACION
    FROM asistencia_estudiante ae
    JOIN estudiante e ON ae.EstudianteID = e.EstudianteID
    JOIN horario h ON ae.HorarioID = h.HorarioID
    JOIN curso c ON h.CursoID = c.CursoID
    WHERE ae.AsistenciaID = p_AsistenciaID;
END $$

DELIMITER ;




DELIMITER $$

CREATE TRIGGER trg_update_dia_asistencia
BEFORE UPDATE ON ASISTENCIA_ESTUDIANTE
FOR EACH ROW
BEGIN
    DECLARE dia_en_ingles VARCHAR(20);
    DECLARE dia_en_espanol VARCHAR(20);

    SET dia_en_ingles = DAYNAME(NEW.FECHADECLASE);

    SET dia_en_espanol = CASE dia_en_ingles
        WHEN 'Monday' THEN 'Lunes'
        WHEN 'Tuesday' THEN 'Martes'
        WHEN 'Wednesday' THEN 'Miércoles'
        WHEN 'Thursday' THEN 'Jueves'
        WHEN 'Friday' THEN 'Viernes'
        WHEN 'Saturday' THEN 'Sábado'
        WHEN 'Sunday' THEN 'Domingo'
    END;

    SET NEW.DIAASISTENCIA = dia_en_espanol;
END$$

DELIMITER ;

SELECT DAYNAME('2024-02-16');


SHOW VARIABLES LIKE 'character_set_database';
SHOW VARIABLES LIKE 'collation_database';

ALTER DATABASE bd_control_asistencia_alumnadoprueba CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE asistencia_estudiante CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;



SELECT @@lc_time_names;

SHOW VARIABLES LIKE 'character_set_database';
SHOW VARIABLES LIKE 'collation_database';
SHOW TABLE STATUS WHERE Name = 'asistencia_estudiante';

ALTER DATABASE bd_control_asistencia_alumnadoprueba CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


SHOW TABLE STATUS FROM bd_control_asistencia_alumnadoprueba;


DELIMITER $$

CREATE PROCEDURE ObtenerAsistenciaPorEstudianteYHorario(
    IN p_EstudianteID INT,
    IN p_HorarioID INT
)
BEGIN
    SELECT DISTINCT 
        ae.*,  -- Trae todas las columnas de asistencia_estudiante
        e.Nombres, 
        e.TipoDocumento,
        e.Apellidos,
        e.NumeroDocumento,
        c.NombreCurso,
        h.FechaInicio, 
        h.FechaFin,
        h.DiaSemana,
        p.Nombres AS profesor,
        p.Apellidos AS  profesorApe
    FROM asistencia_estudiante ae
    INNER JOIN estudiante e ON ae.EstudianteID = e.EstudianteID
    INNER JOIN horario h ON ae.HorarioID = h.HorarioID
    INNER JOIN curso c ON h.CursoID = c.CursoID
    INNER JOIN profesor p ON h.ProfesorID = p.ProfesorID
    WHERE ae.EstudianteID = p_EstudianteID 
    AND ae.HorarioID = p_HorarioID;
    
END$$

DELIMITER ;

call ObtenerAsistenciaPorEstudianteYHorario(1,1)





DELIMITER $$

CREATE PROCEDURE GenerarAsistenciaAutomatica(
    IN p_HorarioID INT,
    IN p_EstudianteID INT
)
BEGIN
    DECLARE fecha_actual DATE;
    DECLARE fecha_fin DATE;
    
    -- Obtener las fechas de inicio y fin del horario
    SELECT FechaInicio, FechaFin INTO fecha_actual, fecha_fin
    FROM horario
    WHERE HorarioID = p_HorarioID;

    -- Si el horario no existe, lanzar un error
    IF fecha_actual IS NULL OR fecha_fin IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: No se encontró el horario especificado.';
    END IF;

    -- Recorrer todas las fechas dentro del rango y registrar asistencia
    WHILE fecha_actual <= fecha_fin DO
        -- Insertar solo si no existe ya un registro para esa fecha
        IF NOT EXISTS (
            SELECT 1 FROM asistencia_estudiante
            WHERE HorarioID = p_HorarioID 
            AND EstudianteID = p_EstudianteID 
            AND FECHADECLASE = fecha_actual
        ) THEN
            INSERT INTO asistencia_estudiante (EstudianteID, HorarioID, FECHADECLASE, Comentario, EstadoAsistencia, UsuarioRegistro)
            VALUES (
                p_EstudianteID,
                p_HorarioID,
                fecha_actual,
                'Generado automáticamente',
                'En espera', -- Puedes cambiar a "Presente" si lo deseas
                'admin'
            );
        END IF;
        
        -- Avanzar al siguiente día
        SET fecha_actual = DATE_ADD(fecha_actual, INTERVAL 1 DAY);
    END WHILE;
END $$

DELIMITER ;


