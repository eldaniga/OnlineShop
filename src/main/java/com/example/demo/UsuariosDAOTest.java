package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UsuariosDAOTest {


    public  ArrayList<UsuarioDTO> leeUsuarios(){
        ArrayList<UsuarioDTO> lista = new ArrayList();
        UsuarioDTO usuario1 = new UsuarioDTO("eldanyga", "eldanyga123");
        UsuarioDTO usuario2 = new UsuarioDTO("admin", "admin");
        UsuarioDTO usuario3 = new UsuarioDTO("pepito", "teamolola");
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);


        return lista;
    }





}
