package com.example.demo;

import java.util.ArrayList;

public interface UsuariosDAOInterface {

    public ArrayList<UsuarioDTO> leeUsuarios( );
    public boolean insertaUsuario(UsuarioDTO usuario);
}