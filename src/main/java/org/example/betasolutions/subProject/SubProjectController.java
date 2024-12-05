package org.example.betasolutions.subProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/project/subproject/edit")
    public String editSubProject() {
        return "subprojectpage";
    }

    @PostMapping("/project/subproject/delete")
    public String deleteSubProject(){
        return "redirect:/project";
    }
    @PostMapping("/project/subproject/new")
    public String createNewSubProject(){
        return "redirect:/project";
    }

}
