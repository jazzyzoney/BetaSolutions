package org.example.betasolutions.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("project/task/new")
    public String createNewTask(){
        return "redirect:/project";
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
