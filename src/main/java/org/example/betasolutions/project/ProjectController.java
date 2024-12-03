package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private ProjectService projectService;
    private final HttpSession session;

    public ProjectController(ProjectService projectService, HttpSession session) {
        this.projectService = projectService;
        this.session = session;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        //i have an idea with requestparam for active projects and inactive projects to show on the homepage but i am not sure how it work with the html stuff
        model.addAttribute("project", new Project());
        int active =1; //just for now to see if it works
        model.addAttribute("ProfileID", session.getAttribute("ProfileID"));
        model.addAttribute("project_overview", projectService.readAllProjects());
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
