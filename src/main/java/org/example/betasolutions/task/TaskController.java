package org.example.betasolutions.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    //this one is for creating a new task on a project
    @GetMapping("/project/{projectID}/New/task")
    public String createNewTaskForProject(Model model, @PathVariable("projectID") int projectID){
        model.addAttribute("projectID", projectID);
        model.addAttribute("task", new Task());
        return "taskpage";
    }

    //this one is for creating a new task on a project
    @PostMapping("project/{projectID}/task/new/post")
    public String createNewTaskForProjectPost(@PathVariable("projectID") int projectID, @ModelAttribute Task task){
        task.setProjectID(projectID);
        taskService.createTaskForProejct(task);
        return "redirect:/project/" + projectID;
    }

    @GetMapping("/project/{projectID}/subproject/{subProjectID}/New/task")
    public String createNewTaskForSubProject(Model model, @PathVariable("projectID") int projectID, @PathVariable("subProjectID") int subProjectID){
        model.addAttribute("projectID", projectID);
        model.addAttribute("subProjectID", subProjectID);
        model.addAttribute("task", new Task());
        return "taskpageforSubProject";
    }

    @PostMapping("/project/{projectID}/subproject/{subProjectID}/New/task/post")
    public String createNewTaskForSubProjectPost(@PathVariable("projectID") int projectID, @PathVariable("subProjectID") int subProjectID,@ModelAttribute Task task){
        task.setProjectID(projectID);
        task.setSubProjectID(subProjectID);
        taskService.createTaskForSubProject(task);
        return "redirect:/project/" + projectID;
    }

    @PostMapping("/project/task/edit")
    public String editTask(){
        return "redirect:/project";
    }
    @PostMapping("project/task/delete")
    public String deleteTask(){
        return "redirect:/project";
    }
}
