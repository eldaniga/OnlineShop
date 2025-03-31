DROP DATABASE usuarios_tienda;

CREATE DATABASE usuarios_tienda;


USE usuarios_tienda;


CREATE TABLE usuarios(
	ID int auto_increment,
     nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    alias VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(80) NOT NULL UNIQUE,
    contraseña VARCHAR(50) NOT NULL,
   PRIMARY KEY (id, email, alias)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alias VARCHAR(50) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL,
    FOREIGN KEY (alias) REFERENCES usuarios(alias) ON DELETE CASCADE
);