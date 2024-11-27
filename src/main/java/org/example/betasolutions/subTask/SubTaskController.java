package org.example.betasolutions.subTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubTaskController {
    private SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService){
        this.subTaskService = subTaskService;
    }

    //@PostMapping("project/subtask/edit") return "redirect:/project"
    //@PostMapping("project/subtask/delete") return "redirect:/project"
    //@PostMapping("/project/subtask/new") return "redirect:/project";
}
