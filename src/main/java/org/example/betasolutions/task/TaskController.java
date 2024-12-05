package org.example.betasolutions.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("project/{projectID}/task/{taskID}")
    public String getTask(Model model, @PathVariable int projectID, @PathVariable int taskID){
        Task task = taskService.getAllTasks()
        model.addAttribute("task", task)
        return "taskPage";
    }

    @PostMapping("project/task/new")
    public String createNewTask(){
        return "redirect:/project";
    }
    @PostMapping("/project/task/edit")
    public String editTask(@ModelAttribute Task task){

        taskService.editTime(task, 2);

        return "redirect:/project";
    }

    @PostMapping("project/task/delete")
    public String deleteTask(){
        return "redirect:/project";
    }
}
