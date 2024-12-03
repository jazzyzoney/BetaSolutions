package org.example.betasolutions.login;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addText("login", new Login());
        return "loginpage";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Login login){
        //if the login is authenticated, the login is redirected to their homepage
        return "redirect: /home";
    }

    @GetMapping("/login/new")
    public String getNewProfilePage(Model model){
        model.addText("newProfile", new Login());
        return "newProfile";
    }

    @PostMapping("/login/new")
    public String postNewLogin(Model model){
        return "redirect: /login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect: /login";
    }

}
