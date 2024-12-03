package org.example.betasolutions.subTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubTaskController {
    private SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService){
        this.subTaskService = subTaskService;
    }

    @PostMapping("project/subtask/edit")
    public String editSubTask(){
        return "redirect:/project";
    }

    @PostMapping("project/subtask/delete")
    public String deleteSubTask(){
        return "redirect:/project";
    }
    @PostMapping("/project/subtask/new")
    public String createNewSubTask(){
        return "redirect:/project";
    }
}
