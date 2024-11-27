package org.example.betasolutions.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(){
        return "loginpage";
    }

    @PostMapping("/login")
    public String postLogin(){
        return "redirect: /home";
    }

    @GetMapping("/login/new")
    public String getNewProfilePage(){
        return "newProfile";
    }

    @PostMapping("/login/new")
    public String postNewLogin(){
        return "redirect: /login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect: /login";
    }

}
