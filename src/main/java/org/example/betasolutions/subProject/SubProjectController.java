package org.example.betasolutions.subProject;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class SubProjectController {
    private SubProjectService subProjectService;
    private final HttpSession session;

    public SubProjectController(SubProjectService subProjectService, HttpSession session) {
        this.subProjectService = subProjectService;
        this.session = session;
    }

    @GetMapping("/project/{id}/subproject")
    public String getSubProjects(Model model,int projectID){
        model.addAttribute("subproject", new SubProject());
        model.addAttribute("subproject_overview",subProjectService.readAllSubProjects(projectID));
        return "projectpage";
    }
    @GetMapping("/project/{projectID}/subproject/newSubProject")
    public String createNewSubProject(@PathVariable ("projectID") int projectID,Model model){
        System.out.print(projectID);
        model.addAttribute("projectID", projectID);
        System.out .println("id: " + projectID);
        SubProject subProject = new SubProject();
        model.addAttribute("subproject",subProject);
        return "newSubproject";
    }
    @PostMapping("/project/{projectID}/subproject/newSubProject/post")
    public String postNewSubProject(@PathVariable("projectID") int projectID, @ModelAttribute SubProject subProject) {
        subProject.setProjectID(projectID);
        subProjectService.insertIntoSubProject(subProject);
        return "redirect:/project/" + projectID;
    }

    /*
    @PostMapping("/project/subproject/edit")
    public String editSubProject() {
        return "subprojectpage";
    }
*/
    @PostMapping("/project/subproject/delete")
    public String deleteSubProject(@RequestParam int subprojectID,  @RequestParam int projectID){
        subProjectService.deleteSubProject(subprojectID);
        return "redirect:/project/" + projectID;
    }
/*
    @PostMapping("/project/subproject/new")
    public String createNewSubProject(){
        return "redirect:/project";
    }
*/

}
