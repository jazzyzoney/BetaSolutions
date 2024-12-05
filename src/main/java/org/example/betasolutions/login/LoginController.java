package org.example.betasolutions.login;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login)")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

@GetMapping("/login")
public String getLogin(Model model) {
    model.addAttribute("login", new Login());
    return "login";
}

@PostMapping("/login")
public String postLogin(@ModelAttribute Login login) {
    return "redirect:/home";
}

@GetMapping("/login/new")
public String getNewProfilePage() {
    return "newProfile";
}

@PostMapping("/login/new")
public String postNewLogin() {
    return "redirect:/login";
}

@PostMapping("/logout")
public String logout() {
    return "redirect:/login";
}



}
