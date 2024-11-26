package org.example.betasolutions.login;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }
}
