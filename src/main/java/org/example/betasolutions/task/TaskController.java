package org.example.betasolutions.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //@PostMapping("project/task/new") return "redirect:/project"
    //@PostMapping("/project/task/edit") return "redirect:/project"
    //@PostMapping("project/task/delete") return "redirect:/project"
}
