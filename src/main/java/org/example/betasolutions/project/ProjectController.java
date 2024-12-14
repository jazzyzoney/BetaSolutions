package org.example.betasolutions.project;
import jakarta.servlet.http.HttpSession;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.subProject.SubProjectRepository;
import org.example.betasolutions.subTask.SubTask;
import org.example.betasolutions.subTask.SubTaskService;
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
    private SubTaskService subTaskService;

    public ProjectController(ProjectService projectService, HttpSession session, SubProjectRepository subProjectRepository, TaskService taskService, SubTaskService subTaskService) {
        this.projectService = projectService;
        this.session = session;
        this.subProjectRepository = subProjectRepository;
        this.taskService = taskService;
        this.subTaskService = subTaskService;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        //i have an idea with requestparam for active projects and inactive projects to show on the homepage but i am not sure how it work with the html stuff
        //model.addAttribute("project", new Project());
        model.addAttribute("profileID", session.getAttribute("profileID"));
        model.addAttribute("projectList", projectService.readAllProjects());
        return "homepage";
    }
    @GetMapping("/project/new")
    public String createNewProject(Model model){
        model.addAttribute("project", new Project());
        return "newproject";
    }

    @PostMapping("/project/new")
    public String createNewProject(@ModelAttribute Project project){
        projectService.insertAssignmentIntoTable(project);
        return "redirect:/home";
    }

    //does this need pathvariable?
    @GetMapping("/project/{projectID}")
    public String getProject(@PathVariable("projectID") int projectID,Model model) {
        Project project = projectService.readAllProjects().get(projectID - 1);
        List<SubProject> subProjects = subProjectRepository.readAllSubProjects(projectID);
        List<Task> tasks = taskService.getAllTasksBelongingToProject(projectID);

        Map<Task,Integer> subTaskCount = new HashMap<>();
        Map<SubProject, List<Task>> subProjectsAndTasks = new HashMap<>();
        List<Task> tasksWithoutSubProject = new ArrayList<>();

        //for each subproject we are adding a list of tasks to the map subProjectsAndTasks with the subproject as key and the list of tasks as value
        for (SubProject subProject : subProjects) {
            subProjectsAndTasks.put(subProject, new ArrayList<>());
        }
        //here we are counting the amount of subtasks for each task and adding it to the map SubTaskCount with the task as key and the amount of subtasks as value
        for (Task task : tasks) {

            //count them subtasks
            int subTaskCounter = subTaskService.readAllSubTasks(projectID, task.getID()).size();

            subTaskCount.put(task, subTaskCounter);


            if (task.getSubProjectID() != 0) {
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
        model.addAttribute("subTaskCount", subTaskCount);
        return "projectpage";
    }

    @PostMapping("project/delete")
    public String deleteProject(@RequestParam int project_id){
        projectService.deleteProject(project_id);
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
