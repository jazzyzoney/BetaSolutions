package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    /*
    @GetMapping("/home") return "homepage"
    @GetMapping("/home/new") return "homepage"

    @GetMapping("/project") return "projectpage"
    @PostMapping("project/delete") return "redirect:/home"
    @PostMapping("project/edit") return "redirect:/project"

    @GetMapping("/project/budget") return "budgetpage"


    */
}
