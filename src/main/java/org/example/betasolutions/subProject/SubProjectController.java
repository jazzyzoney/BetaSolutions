package org.example.betasolutions.subProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubProjectController {
    private SubProjectService subProjectService;

    public SubProjectController(SubProjectService subProjectService){
        this.subProjectService = subProjectService;
    }

    @PostMapping("/project/subproject/edit")
    public String editSubProject() {
        return "redirect:/project";
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
