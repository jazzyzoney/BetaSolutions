package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.example.betasolutions.subProject.SubProject;
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
        //model.addAttribute("project", new Project());

        model.addAttribute("profileID", session.getAttribute("profileID"));
        model.addAttribute("projectList", projectService.readAllProjects());
        return "homepage";
    }
    @PostMapping("/project/new")
    public String createNewProject(@ModelAttribute Project project){
        projectService.insertAssignmentIntoTable(project);
        return "redirect: /home";
    }

    //does this need pathvariable?
    @GetMapping("/project/{projectID}")
    public String getProject(Model model, @PathVariable int projectID){
        //int projectID = (int)session.getAttribute("projectID");
        System.out.println(projectID);

        Project project = projectService.readAllProjects().get(projectID - 1);//readProjectByID(projectID);
        System.out.println(project.getName());

        model.addAttribute("project", project );

        //subproject, task, subtask;
        return "projectpage";
    }

/*
    @PostMapping("/project")
    public String getProject(@ModelAttribute int projectID){//Project project){
        //int projectID = project.getID();
        session.setAttribute("projectID", projectID);
        //System.out.println(project.getName());
        System.out.println(session.getAttribute("projectID"));
        return "redirect:/project";
    }*/

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
