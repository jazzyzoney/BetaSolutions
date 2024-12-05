package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.example.betasolutions.subProject.SubProjectRepository;
import org.example.betasolutions.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private final SubProjectRepository subProjectRepository;
    private ProjectService projectService;
    private final HttpSession session;
    private TaskService taskService;

    public ProjectController(ProjectService projectService, HttpSession session, SubProjectRepository subProjectRepository,TaskService taskService) {
        this.projectService = projectService;
        this.session = session;
        this.subProjectRepository = subProjectRepository;
        this.taskService = taskService;
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
    @GetMapping("/project/{id}")
    public String getProject(@PathVariable("id") int projectID,Model model){
        Project project = projectService.readAllProjects().get(projectID - 1); //readProjectByID(projectID);
        model.addAttribute("subproject_overview", subProjectRepository.readAllSubProjects(projectID));
        model.addAttribute("task_overview", taskService.getAllTasks(projectID));
        //session.setAttribute("project_id", subProjectID);
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
