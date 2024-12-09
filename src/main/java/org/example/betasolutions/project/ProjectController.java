package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.subProject.SubProjectRepository;
import org.example.betasolutions.task.Task;
import org.example.betasolutions.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getProject(@PathVariable("projectID") int projectID,Model model) {
        Project project = projectService.readAllProjects().get(projectID - 1);
        List<SubProject> subProjects = subProjectRepository.readAllSubProjects(projectID);
        List<Task> tasks = taskService.getAllTasks(projectID);

        Map<SubProject, List<Task>> subProjectsAndTasks = new HashMap<>();
        List<Task> tasksWithoutSubProject = new ArrayList<>();


        for (SubProject subProject : subProjects) {
            subProjectsAndTasks.put(subProject, new ArrayList<>());
        }

        for (Task task : tasks) {
            if (task.getSubProjectID() != 0) {
                System.out.println("Task ID: " + task.getName() + " SubProject ID: " + task.getSubProjectID());
                for (SubProject subProject : subProjects) {
                    if (task.getSubProjectID() == subProject.getID()) {
                        subProjectsAndTasks.get(subProject).add(task);
                        break;
                    }
                }
            } else {
                tasksWithoutSubProject.add(task);
            }
        }
        model.addAttribute("project", project);
        model.addAttribute("subProjects", subProjectsAndTasks);
        model.addAttribute("tasksWithoutSubProject", tasksWithoutSubProject);
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
