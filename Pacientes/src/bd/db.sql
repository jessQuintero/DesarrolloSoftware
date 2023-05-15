DROP DATABASE IF EXISTS pacientes_iud;

CREATE DATABASE IF NOT EXISTS pacientes_iud;

USE pacientes_iud;

CREATE TABLE tipos_identificacion(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(45) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE estados_civiles(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(45) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE roles_parentescos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(45) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE pacientes(
    id INT NOT NULL AUTO_INCREMENT,
    numero_identificacion VARCHAR(45) NOT NULL,
    nombres VARCHAR(45) NOT NULL,
    apellidos VARCHAR(45) NOT NULL,
    sexo CHAR(2) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(45) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_creacion DATETIME DEFAULT NOW(),
    fecha_actualizacion DATETIME DEFAULT NOW(),
    tipos_identificacion_id INT NOT NULL,
    estados_civiles_id INT NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(numero_identificacion),
    FOREIGN KEY(tipos_identificacion_id) REFERENCES tipos_identificacion(id),
    FOREIGN KEY(estados_civiles_id) REFERENCES estados_civiles(id)
);

CREATE TABLE grupo_familiar(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    fecha_creacion DATETIME DEFAULT NOW(),
    fecha_actualizacion DATETIME DEFAULT NOW(),
    pacientes_id INT NOT NULL,
    roles_parentescos_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(pacientes_id) REFERENCES pacientes(id),
    FOREIGN KEY(roles_parentescos_id) REFERENCES roles_parentescos(id)
);

INSERT INTO tipos_identificacion(nombre, descripcion)
VALUES('CC','Cédula de Ciudadanía');
INSERT INTO tipos_identificacion(nombre, descripcion)
VALUES('CE','Cédula de Extranjería');
INSERT INTO tipos_identificacion(nombre, descripcion)
VALUES('TI','Tarjeta de Identidad');
INSERT INTO tipos_identificacion(nombre, descripcion)
VALUES('PE','Pasaporte');
INSERT INTO tipos_identificacion(nombre, descripcion)
VALUES('PT','Permiso de trabajo');

INSERT INTO estados_civiles(nombre,descripcion)
VALUES ('SOL','Soltero');
INSERT INTO estados_civiles(nombre,descripcion)
VALUES ('CAS','Casado');
INSERT INTO estados_civiles(nombre,descripcion)
VALUES ('UL','Unión Libre');
INSERT INTO estados_civiles(nombre,descripcion)
VALUES ('VIU','Viudo');
INSERT INTO estados_civiles(nombre,descripcion)
VALUES ('DIV','Divorciado');

INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('ABU','Abuelo o Abuela');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('PAD','Padre');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('MAD','Madre');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('ESP','Esposa o Esposo');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('HIJ','Hijo o Hija');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('SUE','Suegra o Suegro');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('HER','Hermana o Hermano');
INSERT INTO roles_parentescos(nombre,descripcion)
VALUES('PRI','Primo o Prima');

