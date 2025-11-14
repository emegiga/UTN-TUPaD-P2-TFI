-- 1er script: Crear DB y tablas
CREATE DATABASE IF NOT EXISTS tfi_grupo147_v2;
USE tfi_grupo147_v2;

DROP TABLE IF EXISTS Vehiculo;
DROP TABLE IF EXISTS SeguroVehicular;

CREATE TABLE SeguroVehicular (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN,
    aseguradora VARCHAR(80) NOT NULL,
    nroPoliza VARCHAR(50) UNIQUE,
    cobertura ENUM('RC','TERCEROS','TODO_RIESGO') NOT NULL,   -- enum
    vencimiento DATE NOT NULL
);

CREATE TABLE Vehiculo (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    eliminado BOOLEAN,
    dominio VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT(4),
    nroChasis VARCHAR(50) UNIQUE,
    seguro_id BIGINT UNIQUE NOT NULL,
    FOREIGN KEY (seguro_id) REFERENCES SeguroVehicular(id) ON DELETE CASCADE 
);

