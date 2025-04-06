package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.net.http.HttpRequest;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

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
                Usuario usuario = (Usuario) session.getAttribute("usuario");

                if(usuario.getRole().equals("ADMIN")){
                    List<Usuario> usuarios = dao.leeUsuarios();
                    model.addAttribute("usuarios", usuarios);
                    return "admin";
                }else{
                    model.addAttribute("mensajeToast", "Operación completada con éxito!");
                    return "productos";
                }

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
        if(request.getSession(false) != null){
            try{
                Usuario usuarioFinal = dao.findRoleByUsername(usuario);

                //System.out.println(usuarioFinal.getRole());
                if(usuarioFinal.getAlias().equals(usuario) && usuarioFinal.getPassword().equals(password) && usuarioFinal.getRole().equals("USER")){
                    HttpSession session = request.getSession(false);
                    //Usuario usuarioObjeto = new Usuario(usuario, password);
                    session.setAttribute("usuario", usuarioFinal);
                    model.addAttribute("mensajeToast", "Operación completada con éxito!");

                    return "productos";

                }else if(usuarioFinal.getAlias().equals(usuario) && usuarioFinal.getPassword().equals(password) && usuarioFinal.getRole().equals("ADMIN")){
                    HttpSession session = request.getSession(false);
                    //UsuarioDTO usuarioObjeto = new UsuarioDTO(usuario, password);
                    session.setAttribute("usuario", usuarioFinal);

                    List<Usuario> usuarios = dao.leeUsuarios();
                    System.out.println("Lista: " + usuarios);

                    model.addAttribute("usuarios", usuarios);
                    model.addAttribute("mensajeToast", "Disfruta de tu vista, " + usuario);

                    return "admin";
                }
            }catch (TemplateInputException e){
                model.addAttribute("mensajeToast", "Ha ocurrido un error!!");
                return "formulario2";
            }catch (EmptyResultDataAccessException e){
                model.addAttribute("mensajeToast", "No se encuentra el usuario dentro de nuestra Base de Datos");
                model.addAttribute("isBad", true);
                return "formulario2";
            }

        }

        return "formulario2";

        //its worked
    }


    @PostMapping("/datosusuario")
    public String datosUsuarios(HttpServletRequest request, HttpServletResponse response, Model model) {
        if(request.getSession(false) != null){ //if we have an actived session
            try{
                // Se leen los parámetros
                HttpSession session = request.getSession(false);

                String nombre = request.getParameter("name_form");
                String apellidos = request.getParameter("surnname_form");
                String alias = request.getParameter("alias_form");
                String email = request.getParameter("email_form");
                String password = request.getParameter("password_form");


                System.out.println(nombre + apellidos +email+password);
                // Se crea el objeto usuario (se supone que existe la clase Usuario)
                //por defecto creo los usuarios con rol USER
                Usuario usuarioObject = new Usuario(nombre, apellidos,alias, email, password, "USER");
                System.out.println(dao.insertaUsuario(usuarioObject));
                session.setAttribute("usuario", usuarioObject);

                //model.addAttribute("usuario", usuarioObject);
                model.addAttribute("mensajeToast", "Se ha registrado con éxito!");

                return "formulario";

            }catch (Exception SQLIntegrityConstraintViolationException){
                return "formulario2";
            }

        }
        return "formulario2";
    }

    @PostMapping("/eliminar/{alias}")
    public String eliminarUsuario(@PathVariable("alias") String alias) {
        // Lógica para eliminar el usuario
        try{
            if(dao.eliminarUsuario(alias) == 1){
                System.out.println("Se ha elminado correcctamente");
            }else{
                System.out.println("Problemas al eliminar usuario");
            }

        }catch (Exception e){
            return e.getMessage();
        }

        // Redirigir a la lista de usuarios después de eliminar
        return "admin";
    }
    @GetMapping("/admin")
    public String adminView(HttpServletRequest request, Model model){
        List<Usuario> usuarios = dao.leeUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "admin";
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
