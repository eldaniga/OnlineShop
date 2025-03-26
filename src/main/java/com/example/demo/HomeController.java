package com.example.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


    @GetMapping("/")
    public String retornarForm(){
        return "formulario2";
    };

    @GetMapping("/horasesion")
    public String horasesion (HttpServletRequest request, Model model){
            HttpSession sesion = request.getSession(true);
            Date date = (Date)sesion.getAttribute("date");
            String mensajeAcceso;
            
            if(date !=null){
                mensajeAcceso = "Ultimo acceso de la sesion "+date;
            }else{
                mensajeAcceso = "Este es el primer acceso de la sesion";
            }

            date = new Date();
            sesion.setAttribute("date", date);
            String mensajeFecha = "Fecha Actual: " + date;
            model.addAttribute("mensajeAcceso", mensajeAcceso);
            model.addAttribute("mensajeFecha", mensajeFecha);

            return "horasesion";


    }

    @GetMapping("/addCookie")
    public String ejemplocookie(HttpServletResponse response){
        Cookie c = new Cookie("userIdCookie", "12345");

        c.setMaxAge(60*60*24*365*2);
        c.setPath("/");
        response.addCookie(c);
        return "index2";
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
            //si no existe una sesion asociada, reenvia al login
            return "formulario2";
        }
        session.setAttribute("usuario", usuario);


        return "responsed";
    }
}
