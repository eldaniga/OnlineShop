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
    //peticion que esperamos recibir

    @GetMapping("/")
    public String retornar(){
        return "index2";
    };

    @GetMapping("/formulario")
    public String retornarForm(){
        return "formulario";
    };
    @PostMapping("/trabajo")
    public String retornarOK(@RequestParam("email_form") String email, @RequestParam("password_form") String password){
        System.out.println("Email: " + email + " Password: " + password);
        return "respuestaTrabajo";
    };
    @GetMapping("/jacinto")
    public String retornarJacinto () {
        return "index2";
    }

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





}
