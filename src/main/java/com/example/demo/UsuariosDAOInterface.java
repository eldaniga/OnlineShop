package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuariosDAOInterface {

    public ArrayList<UsuarioDTO> leeUsuarios( );
    public boolean insertaUsuario(UsuarioDTO usuario);
}