package com.example.demo;

public class UsuarioDTO {
    private String alias;
    private String contraseña;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor con parámetros
    public UsuarioDTO(String alias, String contraseña) {
        this.alias = alias;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public String getUsuario() {
        return alias;
    }

    public void setUsuario(String alias) {
        this.alias = alias;
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
                "usuario='" + alias+ '\'' +
                ", contraseña='********'}"; // Ocultar contraseña por seguridad
    }


}
