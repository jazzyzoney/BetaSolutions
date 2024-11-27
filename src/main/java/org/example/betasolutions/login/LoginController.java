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
    @GetMapping("/login")
    @PostMapping("/login")
    @GetMapping("/login/new")
    @PostMapping("/login/new")
    @PostMapping("/logout")*/

}
