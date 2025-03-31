package com.example.demo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellidos;
    private String usuario;

    private String email;
    private String password;
    private String role;



    public Usuario(){
        this.nombre = "";
        this.apellidos = "";
        this.usuario = "";
        this.email="";
        this.password = "";
        this.role="";

    }
    // Constructor
    public Usuario(String nombre, String apellidos, String usuario, String email, String password, String role) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.role = role;

    }


    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }



    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setUsuario(String usuario) {
        this.usuario=usuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role=role;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
