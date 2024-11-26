package org.example.betasolutions.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

}
