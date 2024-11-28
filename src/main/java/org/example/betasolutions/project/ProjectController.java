package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.example.betasolutions.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getProject(Model model){
        int projectID = 1; //should be from session.
        Project project = projectService.getProject(projectID); //find project by ID.
        model.addAttribute(project); //add project-object to Thymeleaf.
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
