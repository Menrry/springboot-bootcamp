package seguranca.springboot_bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/public")
    @ResponseBody
    public String publicPage() {
        return "Esta es una página pública.";
    }
    
    @GetMapping("/user")
    @ResponseBody
    public String userPage() {
        return "Esta es una página para usuarios.";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminPage() {
        return "Esta es una página para administradores.";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // Assumes you have a home.html in src/main/resources/templates
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Assumes you have a login.html in src/main/resources/templates
    }
        
}