package com.example.demo;

public class UsuarioDTO {
    private String usuario;
    private String contraseña;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor con parámetros
    public UsuarioDTO(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "usuario='" + usuario + '\'' +
                ", contraseña='********'}"; // Ocultar contraseña por seguridad
    }


}
