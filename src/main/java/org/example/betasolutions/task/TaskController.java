package org.example.betasolutions.task;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final HttpSession session;
    private TaskService taskService;

    public TaskController(TaskService taskService, HttpSession session){
        this.taskService = taskService;
        this.session = session;
    }

    @GetMapping("project/{projectID}/task/{taskID}")
    public String getTask(Model model, @PathVariable int projectID, @PathVariable int taskID){
        Task task = taskService.getTask(projectID, taskID);
        session.setAttribute("task", task);
        System.out.println("task contr. start date: " + task.getStartDate());

        model.addAttribute("task", task);
        model.addAttribute("hours", task.getHours());
        model.addAttribute("projectID", projectID);
        return "taskpage";
    }

    @PostMapping("project/task/new")
    public String createNewTask(){
        return "redirect:/project";
    }
    @PostMapping("/project/task/edit")
    public String editTask(@ModelAttribute int hours){

        Task task = (Task) session.getAttribute("task");// taskService.getTask(projectID, taskID);

        System.out.println("task contr. start date: " + task.getStartDate());
        System.out.println("task contr. id: " + task.getID());
        System.out.println("task contr. hours: " + task.getHours());

        taskService.updateHours(task, hours);

        return "redirect:/project";
    }

    @PostMapping("project/task/delete")
    public String deleteTask(){
        return "redirect:/project";
    }
}
