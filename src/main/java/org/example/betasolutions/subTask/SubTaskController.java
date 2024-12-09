package org.example.betasolutions.subTask;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubTaskController {
    private SubTaskService subTaskService;
    private HttpSession session;

    public SubTaskController(SubTaskService subTaskService, HttpSession session) {
        this.subTaskService = subTaskService;
        this.session = session;
    }

    @GetMapping("/project/{projectID}/task/{taskID}/subtasks")
    public String SubTasksforTask(Model model,@PathVariable ("projectID") int projectID, @PathVariable ("taskID") int taskID){
        model.addAttribute("subtasks", subTaskService.readAllSubTasks(projectID, taskID));
        return "subtaskpage";
    }

    @PostMapping("project/subtask/edit")
    public String editSubTask(){
        return "redirect:/project";
    }

    @PostMapping("/project/subtask/delete")
    public String deleteSubTask(@RequestParam int subTaskID, @RequestParam int projectID, @RequestParam int taskID){
        subTaskService.deleteSubTask(subTaskID);
        return "redirect:/project/" + projectID + "/task/" + taskID + "/subtasks";
    }

    @GetMapping("/project/{projectID}/task/{taskID}/subtasks/new")
    public String createNewSubTask(Model model, @PathVariable ("projectID") int projectID, @PathVariable ("taskID") int taskID){
        model.addAttribute("projectID", projectID);
        model.addAttribute("taskID", taskID);
        model.addAttribute("subtask", new SubTask());
        return "newSubtaskPage";
    }

    @PostMapping("/project/{projectID}/task/{taskID}/subtasks/post")
    public String createNewSubTask(@PathVariable ("projectID") int projectID, @PathVariable ("taskID") int taskID, @ModelAttribute SubTask subTask){
        subTask.setTaskID(taskID);
        subTaskService.createSubTask(subTask);
        return "redirect:/project/" + projectID + "/task/" + taskID + "/subtasks";
    }
}
