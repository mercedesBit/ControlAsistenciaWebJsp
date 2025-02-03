create database bd_control_asistencia_alumnadoPRUEBA;
use bd_control_asistencia_alumnadoPRUEBA;

CREATE TABLE Seccion (
    SeccionID INT AUTO_INCREMENT PRIMARY KEY,
    NombreSeccion VARCHAR(50),
    NumeroAula VARCHAR(20),
    Estado VARCHAR(20),
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE
);
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

CREATE TABLE Ciclo (
    id_ciclo INT PRIMARY KEY,  -- ID del ciclo (Clave primaria)
    nombre_ciclo VARCHAR(50)not null  
);

CREATE TABLE Curso (
    CursoID INT AUTO_INCREMENT PRIMARY KEY,
    CodigoCurso VARCHAR(20),
    NombreCurso VARCHAR(100),
    Descripcion VARCHAR(255),
    Duracion INT,
    Ciclo INT,  -- Cambiamos Ciclo a CicloID para que sea una clave foránea
    Nivel VARCHAR(50),
    Estado VARCHAR(20),
    Notas TEXT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE,
    FOREIGN KEY (Ciclo) REFERENCES Ciclo(id_ciclo)  -- Referencia a la tabla Ciclo
);







CREATE TABLE PersonalAdministrativo (
    PersonalID INT AUTO_INCREMENT PRIMARY KEY,
    TipoDocumento VARCHAR(10),
    NumeroDocumento VARCHAR(16),
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    CorreoElectronico VARCHAR(100),
    TelefonoMovil VARCHAR(15),
    TelefonoTrabajo VARCHAR(15),
    FechaNacimiento DATE,
    Direccion VARCHAR(200),
    Cargo VARCHAR(100),
    FechaContratacion DATE,
    Estado VARCHAR(20),
    Sueldo DECIMAL(10, 2),
    RedesSociales TEXT,
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE
);


CREATE TABLE Asistencia (
    AsistenciaID INT AUTO_INCREMENT PRIMARY KEY,
    EstudianteID INT,
    CursoID INT,
    PersonalID INT null,
    HoraAsistencia TIMESTAMP,
    Estado VARCHAR(20),
    Comentario TEXT null,
    TipoAsistencia VARCHAR(50),
    FechaRegistro DATE,
    UsuarioRegistro VARCHAR(20),
    FechaActualizacion DATE null,
    FOREIGN KEY (EstudianteID) REFERENCES Estudiante(EstudianteID),
    FOREIGN KEY (CursoID) REFERENCES Curso(CursoID),
    FOREIGN KEY (PersonalID) REFERENCES PersonalAdministrativo(PersonalID)
);


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

DELIMITER $$

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



CREATE TABLE Roles (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    NombreRole VARCHAR(50) NOT NULL
);



CREATE TABLE Usuarios (
    UsuarioID INT PRIMARY KEY AUTO_INCREMENT,
    NombreUsuario VARCHAR(50) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL,
    RoleID INT NOT NULL,
    FechaRegistro DATE,
    Estado VARCHAR(20),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);




-- Insertar datos en la tabla Seccion
INSERT INTO Seccion (NombreSeccion, NumeroAula, Estado, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('Sección A', 'A-101', 'Activo', CURDATE(), 'admin', CURDATE());

INSERT INTO Seccion (NombreSeccion, NumeroAula, Estado, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('Sección B', 'B-202', 'Inactivo', CURDATE(), 'admin', CURDATE());

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



select * from  PadresTutores ;
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




INSERT INTO PersonalAdministrativo 
(TipoDocumento, NumeroDocumento, Nombres, Apellidos, CorreoElectronico, TelefonoMovil, TelefonoTrabajo, FechaNacimiento, Direccion, Cargo, FechaContratacion, Estado, Sueldo, RedesSociales, FechaRegistro, UsuarioRegistro, FechaActualizacion) 
VALUES 
('DNI', '44556677', 'Ana', 'Lopez', 'ana.lopez@mail.com', '987654325', '022345679', '1990-08-30', 'Calle Central 890', 'Secretaria', '2018-06-05', 'Activo', 1500.00, 'LinkedIn: ana.lopez', CURDATE(), 'admin', CURDATE());



INSERT INTO Ciclo (id_ciclo, nombre_ciclo)
VALUES 
(1, 'Primer ciclo'),
(2, 'Segundo ciclo '),
(3,  'Tercer ciclo' ),
(4,  'Cuarto ciclo'),
(5, 'Quinto ciclo'),
(6, 'Sexto ciclo ');


INSERT INTO Curso (CodigoCurso, NombreCurso, Descripcion, Duracion, Ciclo, Nivel, Estado, Notas, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES ('CS101', 'Introducción a la Programación', 'Curso de introducción a la programación en Java', 5, 1, 'Básico', 'Activo', 'Ninguno', '2025-03-01', 'admin', '2025-03-01');





-- Insertar datos en la tabla Roles
INSERT INTO Roles (NombreRole)
VALUES ('Administrador'), ('Profesor'), ('Estudiante'), ('Personal Administrativo');

-- Insertar datos en la tabla Usuarios
INSERT INTO Usuarios (NombreUsuario, Contrasena, RoleID, FechaRegistro, Estado)
VALUES ('admin', 'password123', 1, CURDATE(), 'Activo');




-- AGRREGADO POR TONY-- Insertar datos en la tabla Asistencia

INSERT INTO Asistencia (EstudianteID, CursoID, PersonalID, HoraAsistencia, Estado, Comentario, TipoAsistencia, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES (1, 1, 1, CURTIME(), 'Presente', 'Sin observaciones', 'Presencial', CURDATE(), 'admin', CURDATE());
 




-- COLOCAFR TIPO DE HORARIO PRESENCIAL O VIRTUAL 
CREATE TABLE Horario (
    HorarioID INT AUTO_INCREMENT PRIMARY KEY,        -- Identificador del horario
    CursoID INT,                                     -- Referencia al Curso
    ProfesorID INT,                                  -- Referencia al Profesor
    SeccionID INT,                                   -- Referencia a la Sección
    DiaSemana VARCHAR(100),                          -- Días de la semana (Ej. Lunes, Martes, etc.)
    HoraInicioFin VARCHAR(200),      
    FechaInicio DATE,                                -- Fecha de inicio del horario
    FechaFin DATE,                                   -- Fecha de fin del horario
    MaxEstudiantes INT,                              -- Número máximo de estudiantes permitidos
    Estado VARCHAR(20),                              -- Estado del horario (Activo, Inactivo, etc.)
    FechaRegistro DATE,                              -- Fecha de registro
    UsuarioRegistro VARCHAR(20),                     -- Usuario que registra el horario
    FechaActualizacion DATE,                         -- Fecha de última actualización
    FOREIGN KEY (CursoID) REFERENCES Curso(CursoID),-- Relaciona con la tabla Curso
    FOREIGN KEY (ProfesorID) REFERENCES Profesor(ProfesorID), -- Relaciona con la tabla Profesor
    FOREIGN KEY (SeccionID) REFERENCES Seccion(SeccionID) -- Relaciona con la tabla Sección
);


CREATE TABLE Horario_Estudiante (
    EstudianteID INT,                               -- Referencia al Estudiante
    HorarioID INT,                                  -- Referencia al Horario
    PRIMARY KEY (EstudianteID, HorarioID),          -- Clave primaria compuesta por EstudianteID y HorarioID
    FOREIGN KEY (EstudianteID) REFERENCES Estudiante(EstudianteID), -- Relaciona con la tabla Estudiante
    FOREIGN KEY (HorarioID) REFERENCES Horario(HorarioID)           -- Relaciona con la tabla Horario
);

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



-- Primero, verificamos si el número de estudiantes actuales en el horario es menor que la capacidad máxima:
SELECT COUNT(*) AS CantidadEstudiantes
FROM Horario_Estudiante
WHERE HorarioID = 1;  -- Reemplaza 1 con el HorarioID específico



INSERT INTO Horario (CursoID, ProfesorID, SeccionID, DiaSemana, HoraInicioFin, FechaInicio, FechaFin, MaxEstudiantes, Estado, FechaRegistro, UsuarioRegistro, FechaActualizacion)
VALUES 
(1, 1, 1, 'Lunes - Martes - Miercoles', 'L-M-V 08:00-10:00', '2025-03-01', '2025-06-30', 5, 'Activo', CURDATE(), 'admin', '2025-05-05');




INSERT INTO Horario_Estudiante (EstudianteID, HorarioID)
VALUES 
(21, 2),
(22, 2),
(23, 2),
(24, 2);

-- PRUEBA
INSERT INTO Horario_Estudiante (EstudianteID, HorarioID)
VALUES 
(25, 2),
(26, 2),
(27, 2),
(28, 2),
(29, 2);

