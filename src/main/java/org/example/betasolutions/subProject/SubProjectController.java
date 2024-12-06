package org.example.betasolutions.subProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class SubProjectController {
    private SubProjectService subProjectService;

    public SubProjectController(SubProjectService subProjectService){
        this.subProjectService = subProjectService;
    }

    @GetMapping("/project/{id}/subproject")
    public String getSubProjects(Model model,int projectID){
        model.addAttribute("subproject", new SubProject());
        model.addAttribute("subproject_overview",subProjectService.readAllSubProjects(projectID));
        return "projectpage";
    }
    @GetMapping("/project/{id}/subproject/newSubProject")
    public String createNewSubProject(@PathVariable int id,Model model){
        model.addAttribute("projectID", id);
        model.addAttribute("subproject",new SubProject());
        return "newSubproject";
    }
    @PostMapping("/project/{id}/subproject/newSubProject")
    public String postNewSubProject(@PathVariable int id, @ModelAttribute SubProject subProject){
        System.out.println("id: " + subProject.getID() + "\nname: " + subProject.getID() + "\ndeadline: " + subProject.getDeadline());
        subProject.setProjectID(id);
        subProjectService.insertIntoSubProject(subProject);


        return "redirect:/project/" + id;
    }

    @PostMapping("/project/subproject/edit")
    public String editSubProject() {
        return "subprojectpage";
    }

    @PostMapping("/project/subproject/delete")
    public String deleteSubProject(@RequestParam int subprojectID,  @RequestParam int id){
        subProjectService.deleteSubProject(subprojectID);
        return "redirect:/project/" + id;
    }

    @PostMapping("/project/subproject/new")
    public String createNewSubProject(){
        return "redirect:/project";
    }


}
