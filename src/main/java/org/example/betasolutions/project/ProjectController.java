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
    @GetMapping("/home")
    @GetMapping("/project")
    @GetMapping("/project/budget")
    @GetMapping("/project/employees")

    */
}
