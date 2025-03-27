package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorDB {
        private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
        private static final String USUARIO = "root";
        private static final String CONTRASEÑA = "tu_password";

        public static Connection conectar() throws SQLException {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        }


}
