package org.example.betasolutions.login;
import org.example.betasolutions.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;

    }

    public void createLogin(Login login) {
        loginRepository.createLogin(login);
    }
    public boolean verifyLogin(Login login) {
        return loginRepository.vaifyLogin(login);
    }

}
