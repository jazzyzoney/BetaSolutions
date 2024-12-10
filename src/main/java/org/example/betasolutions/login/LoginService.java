package org.example.betasolutions.login;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
    public boolean validateLogin(Login login) {
        Login storedLogin = loginRepository.findByEmail(login.getEmail());
        return storedLogin != null && storedLogin.getPassword().equals(login.getPassword());
    }

    public void createLogin(Login login) {

        loginRepository.createLogin(login);
    }

    public int findEmployeeByEmail(String email) {
        return loginRepository.findEmployeeByEmail(email);
    }
}
