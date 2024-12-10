package org.example.betasolutions.login;
import org.example.betasolutions.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private  final EmployeeRepository employeeRepository;

    public LoginService(LoginRepository loginRepository, EmployeeRepository employeeRepository) {
        this.loginRepository = loginRepository;
        this.employeeRepository = employeeRepository;
    }
    public boolean validateLogin(Login login) {
        Login storedLogin = loginRepository.findByEmail(login.getEmail());
        return storedLogin != null && storedLogin.getPassword().equals(login.getPassword());
    }

    public void createLogin(Login login) {
        loginRepository.createLogin(login);
    }

    //public int findEmployeeByEmail(String email) {
        //return employeeRepository.findEmployeeByEmail(email);
  //  }
}
