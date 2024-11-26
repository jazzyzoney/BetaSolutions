package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {
    ProjectService projectService;

    public LoginController(ProjectService projectService){
        this.projectService = projectService;
    }
}
