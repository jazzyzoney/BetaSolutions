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
    @PostMapping("/project/new")
    public String createNewProject(@ModelAttribute Project project){
        projectService.insertAssignmentIntoTable(project);
        return "redirect: /home";
    }

    //does this need pathvariable?
    @GetMapping("/project/{projectID}")
    public String getProject(@PathVariable("projectID") int projectID,Model model) {
        Project project = projectService.readAllProjects().get(projectID - 1);//get project by id.

        List<SubProject> subProjects = subProjectRepository.readAllSubProjects(projectID); //read all subprojects
        List<Task> tasks = taskService.getAllTasksBelongingToProject(projectID); //readall tasks
        List<SubTask> subTasks = subTaskService.readAllSubTasks(projectID); //read all subtasks.

        //the maps contain a type of assignment and the assignments below.
        //eg. Subproject and lists of tasks.
        Map<SubProject, List<Task>> subProjectsAndTasks = new HashMap<>(); //all tasks belonging to subprojects.
        Map<Task, List<SubTask>> tasksAndSubTasks = new HashMap<>(); //all subtasks in tasks
        List<Task> tasksWithoutSubProject = new ArrayList<>(); //all tasks, indpendent of subprojects.



        //for each subproject in project, add to Map, and create empty arraylist.
        for (SubProject subProject : subProjects) {
            subProjectsAndTasks.put(subProject, new ArrayList<>());
        }

        //For each task in project :
        for (Task task : tasks) {
            if (task.getSubProjectID() != 0) { //if task has subproject:

                for (SubProject subProject : subProjects) { //for each subproject.
                    if (task.getSubProjectID() == subProject.getID()) { //if task.subprojectID and subproject.subprojectID are equal:
                        subProjectsAndTasks.get(subProject).add(task); //add task to subproject.
                        break; // no need to look at rest of subprojects.
                    }
                }
            } else { //if task doesn't have a subproject
                tasksWithoutSubProject.add(task); //add subproject
            } //end of 'if task.getSubProjectID() != 0)'
        }//end of outer loop.

        for (Task task : tasks) {// for each task in project
            tasksAndSubTasks.put(task, new ArrayList<>()); //add task and new arraylist to hashmap.
        }

        for (Task task : tasks) { //for each task in project
            for (SubTask subTask : subTasks) { //for each subtask in project.
                if (subTask.getTaskID() == task.getID()) { //if subtask.taskID and task.taskID are equal
                    tasksAndSubTasks.get(task).add(subTask); //add subtask to subtasklist in task.
                    break; //no need to look at rest of subtasks.
                }//end of 'if (subTask.getTaskID() == task.getID())'
            }//end of inner for loop (subtask)
        }//end of outer for loop (task)*/


        model.addAttribute("project", project);
        model.addAttribute("subProjects", subProjectsAndTasks);
        model.addAttribute("tasksWithoutSubProject", tasksWithoutSubProject);
        model.addAttribute("tasksAndSubTasks", tasksAndSubTasks);
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
