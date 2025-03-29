DROP DATABASE usuarios_tienda;

CREATE DATABASE usuarios_tienda;


USE usuarios_tienda;


CREATE TABLE usuarios(
	ID int primary KEY auto_increment,
     nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    alias VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL,
    contraseña VARCHAR(50) NOT NULL
   
);