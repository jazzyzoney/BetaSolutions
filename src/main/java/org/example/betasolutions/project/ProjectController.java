package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    /*
    @GetMapping("/home") return "homepage"
    @GetMapping("/project") return "projectpage"
    @GetMapping("/project/budget") return "budgetpage"
    @GetMapping("/project/employees") return "employeepage"

    */
}
