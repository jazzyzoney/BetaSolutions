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
        model.addAttribute("projectID", projectID);
        return "taskpage";
    }

    @PostMapping("project/task/new")
    public String createNewTask(){
        return "redirect:/project";
    }
    @PostMapping("/project/{projectID}/task/{taskID}/edit")
    public String editTask(@PathVariable int projectID, @PathVariable int taskID, @ModelAttribute int hours){

        Task task =  taskService.getTask(projectID, taskID);
        //Task task = new Task()
        //Task newTask = task;
        System.out.println("task contr. start date: " + task.getStartDate());
        System.out.println("task contr. id: " + task.getID());
        System.out.println("task contr. hours: " + task.getHours());

        taskService.editTime(task, hours);

        return "redirect:/project";
    }

    @PostMapping("project/task/delete")
    public String deleteTask(){
        return "redirect:/project";
    }
}
