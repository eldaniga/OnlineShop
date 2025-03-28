package com.example.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


    @GetMapping("/")
    public String retornarForm(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600); //modificar el tiempo de duracion de las cookies temporales
        return "formulario2";
    };

        //delete at the end of the practice
    @PostMapping("/datoslogin")
    public String datosLogin(HttpServletRequest request){
        String usuario = request.getParameter("user_form");
        String password = request.getParameter("password_form");
        System.out.println(usuario + " " + password);
        UsuariosDAOTest dao = new UsuariosDAOTest();
        ArrayList<UsuarioDTO> usuarios = dao.leeUsuarios();

        //its worked
        for(UsuarioDTO usuarioArray : usuarios){
            if(usuarioArray.getUsuario().equals(usuario) && usuarioArray.getContraseña().equals(password)){
                HttpSession session = request.getSession(false);
                UsuarioDTO usuarioObjeto = new UsuarioDTO(usuario, password);
                session.setAttribute("usuario", usuarioObjeto);


                return "productos";
            }
        }
        return "formulario2";
    }
    @PostMapping("/datosusuario")
    public String emailList (HttpServletRequest request, HttpServletResponse response) {
        // Se leen los parámetros
        String nombre = request.getParameter("name_form");
        String apellidos = request.getParameter("surnname_form");
        String email = request.getParameter("email_form");
        String password = request.getParameter("password_form");
        System.out.println(nombre + apellidos +email+password);
        // Se crea el objeto usuario (se supone que existe la clase Usuario)
        Usuario usuario = new Usuario(nombre, apellidos, email, password);

        //String userId = basededatos.inserta(usuario);

        // Dicho string se guardará en una cookie permanente para poder identificar
        // en el futuro al usuario cuando vuelva a navegar por el sitio web
        Cookie c = new Cookie("userIdCookie", email);
        c.setMaxAge(60 * 60 * 24 * 365 * 2);
        c.setPath("/");
        response.addCookie(c);
        //System.out.println(response.getC);
        // De forma adicional, se guarda en la sesión
        // el mismo objeto que en la base de datos
        HttpSession session = request.getSession(false);
        if(session == null){
            //si no existe una sesion asociada, reenvia al register
            return "formulario2";
        }
        session.setAttribute("usuario", usuario);


        return "responsed";
    }


    @GetMapping("/login")
    public String loginSession(HttpSession session){
        if(session == null){  //si la session no existe envíalo a login
            return "formulario";
        }
        if(session.getAttribute("usuario") != null){
            return "productos";  //si existe, envialo a productos
        }
        return "formulario";


    }
    @GetMapping("/logout")
    public String logoutSession(HttpSession session){
        session.invalidate();
        return "formulario2";

    }
}
