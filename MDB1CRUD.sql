-- Script para crear la base de datos Colegio
CREATE DATABASE Colegio;

-- Usar la base de datos Colegio
USE Colegio;

-- Script para crear la tabla Estudiantes
CREATE TABLE estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero ENUM('M', 'F') NOT NULL,
    direccion VARCHAR(100),
    telefono VARCHAR(15),
    correo_electronico VARCHAR(100),
    grado VARCHAR(50),
    fecha_inscripcion DATE NOT NULL
);
