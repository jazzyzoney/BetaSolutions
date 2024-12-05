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

    @GetMapping("project/{projectID}/task/{taskID}")
    public String getTask(Model model, @PathVariable int projectID, @PathVariable int taskID){
        Task task = taskService.getTask(projectID, taskID);
        System.out.println("task contr. start date: " + task.getStartDate());
        model.addAttribute("task", task);
        model.addAttribute("hours", task.getHours());
        return "taskPage";
    }

    @PostMapping("project/task/new")
    public String createNewTask(){
        return "redirect:/project";
    }
    @PostMapping("/project/task/edit")
    public String editTask(@ModelAttribute Task task){
        System.out.println("task contr. start date: " + task.getStartDate());
        System.out.println("task contr. id: " + task.getID());
        System.out.println("task contr. hours: " + task.getHours());

        taskService.editTime(task, 2);

        return "redirect:/project";
    }

    @PostMapping("project/task/delete")
    public String deleteTask(){
        return "redirect:/project";
    }
}
