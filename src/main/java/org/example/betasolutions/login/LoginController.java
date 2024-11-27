package org.example.betasolutions.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    /*
    @GetMapping("/login") return "loginpage"
    @PostMapping("/login") return "redirect: /home"
    @GetMapping("/login/new") return "newProfile"
    @PostMapping("/login/new") return "redirect: /login"
    @PostMapping("/logout") return "redirect: /login"
    */

}
