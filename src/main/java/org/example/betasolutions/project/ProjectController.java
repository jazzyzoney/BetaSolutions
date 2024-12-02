package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/home")
    public String getHome(){
        return "homepage";
    }
    @PostMapping("/project/new")
    public String createNewProject(){
        return "redirect: /home";
    }

    @GetMapping("/project")
    public String getProject(){
        return "projectpage";
    }

    @PostMapping("project/delete")
    public String deleteProject(){
        return "redirect:/home";
    }

    @PostMapping("project/edit")
    public String editProject(){
        return "redirect:/project";
    }

    @GetMapping("/project/budget")
    public String getProjectBudget(){
        return "budgetpage";
    }



}
