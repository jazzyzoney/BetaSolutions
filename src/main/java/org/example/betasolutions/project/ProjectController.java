package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("ProfileID", session.getAttribute("ProfileID"));
        model.addAttribute("project_overview", projectService.readAllProjects());
        return "homepage";
    }
    @PostMapping("/project/new")
    public String createNewProject(@ModelAttribute Project project){
        projectService.insertAssignmentIntoTable(project);
        return "redirect: /home";
    }

    //does this need pathvariable?
    @GetMapping("/project")
    public String getProject(Model model, @ModelAttribute Project project){
        int projectID = project.getID();
        model.addAttribute("project", projectService.readProjectByID(projectID));
        session.setAttribute("project_id", projectID);
        return "projectpage";
    }

    @PostMapping("project/delete")
    public String deleteProject(){
        return "redirect:/home";
    }

    @PostMapping("project/edit")
    public String editProject(Model model,@RequestParam int project_id){
         project_id = (int) session.getAttribute("project_id");
         //not done yet

        return "redirect:/project";
    }

    @GetMapping("/project/budget")
    public String getProjectBudget(){
        return "budgetpage";
    }



}
