package org.example.betasolutions.subProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubProjectController {
    SubProjectService subProjectService;

    public SubProjectController(SubProjectService subProjectService){
        this.subProjectService = subProjectService;
    }

    //PostMapping("/project/edit/subproject") return "redirect:/project";

}
