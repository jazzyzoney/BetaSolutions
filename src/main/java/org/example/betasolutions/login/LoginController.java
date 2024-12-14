package org.example.betasolutions.login;
import org.example.betasolutions.employee.Employee;
import org.example.betasolutions.employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class LoginController {
    private final LoginService loginService;
    private final EmployeeService employeeService;

    public LoginController(LoginService loginService, EmployeeService employeeService) {
        this.loginService = loginService;
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("login", new Login());
        return "loginpage";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Login login, Model Model) {
        if (loginService.verifyLogin(login)) {
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login/new")
    public String getNewLoginPage(Model model) {
        model.addAttribute("login", new Login());
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeOffices", employeeService.GetAllEmployeeOffices());
        return "newProfile";
    }

    @PostMapping("/login/new")
    public String postNewLogin(@ModelAttribute Login login, @ModelAttribute Employee employee) {
        int employeeID = employeeService.createNewEmployee(employee);
        login.setEmployeeID(employeeID);
        loginService.createLogin(login);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }



}
