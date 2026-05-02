CREATE DATABASE IF NOT EXISTS emergencias
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE emergencias;

CREATE TABLE IF NOT EXISTS alertas_emergencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL,
    destino VARCHAR(20) NOT NULL,
    metodo_envio VARCHAR(30) NOT NULL,
    tipo_emergencia VARCHAR(80) NOT NULL,
    ubicacion VARCHAR(120) NOT NULL,
    gravedad VARCHAR(20) NOT NULL,
    paciente_nombre VARCHAR(80) NOT NULL,
    paciente_telefono VARCHAR(20) NOT NULL,
    grupo_sanguineo VARCHAR(5) NOT NULL,
    alergias VARCHAR(255) NOT NULL,
    medicacion VARCHAR(255) NOT NULL,
    contacto_emergencia VARCHAR(120) NOT NULL
);
