package com.example.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@Controller
public class HomeController {
    private final UsuariosDAOTest dao;


    @Autowired  // <-- IMPORTANTE: Permite que Spring inyecte el bean
    public HomeController(UsuariosDAOTest usuariosDAOTest) {
        this.dao = usuariosDAOTest;
    }
    @GetMapping("/")


    public String retornarForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("usuario") != null){
                model.addAttribute("mensajeToast", "Operación completada con éxito!");
                return "productos";
            }
        }else{
            session = request.getSession(true);
        }
        return "formulario2";
    }

        //delete at the end of the practice
    @PostMapping("/datoslogin")
    public String datosLogin(HttpServletRequest request, Model model){
        String usuario = request.getParameter("user_form");
        String password = request.getParameter("password_form");
        System.out.println(usuario + " " + password);

        try{
            Usuario usuarioFinal = dao.findRoleByUsername(usuario);
            if(usuarioFinal.getUsuario().equals(usuario) && usuarioFinal.getPassword().equals(password) && usuarioFinal.getRole().equals("USER")){
                HttpSession session = request.getSession(false);
                UsuarioDTO usuarioObjeto = new UsuarioDTO(usuario, password);
                session.setAttribute("usuario", usuarioObjeto);
                model.addAttribute("mensajeToast", "Operación completada con éxito!");


                return "productos";
        }else if(usuarioFinal.getUsuario().equals(usuario) && usuarioFinal.getPassword().equals(password) && usuarioFinal.getRole().equals("ADMIN")){
                HttpSession session = request.getSession(false);
                UsuarioDTO usuarioObjeto = new UsuarioDTO(usuario, password);
                session.setAttribute("usuario", usuarioObjeto);

                List<Usuario> usuarios = dao.leeUsuarios();
                System.out.println("Lista: " + usuarios);
                model.addAttribute("usuarios", usuarios);
                model.addAttribute("mensajeToast", "Operación completada con éxito!");


                return "admin";
            }
        }catch (Exception e){
                return(e.getMessage());
        }
        return "formulario2";

        //its worked
    }


    @PostMapping("/datosusuario")
    public String emailList (HttpServletRequest request, HttpServletResponse response) {

        // Se leen los parámetros
        String nombre = request.getParameter("name_form");
        String apellidos = request.getParameter("surnname_form");
        String usuario = request.getParameter("alias_form");
        String email = request.getParameter("email_form");
        String password = request.getParameter("password_form");


        System.out.println(nombre + apellidos +email+password);
        // Se crea el objeto usuario (se supone que existe la clase Usuario)
        //por defecto creo los usuarios con rol USER
        Usuario usuarioObject = new Usuario(nombre, apellidos,usuario, email, password, "USER");
        System.out.println(dao.insertaUsuario(usuarioObject));

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
        session.setAttribute("usuario", usuarioObject);


        return "productos";
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
        if(session == null){
            return "formulario2";
        }
        session.invalidate();
        return "formulario2";

    }
}
