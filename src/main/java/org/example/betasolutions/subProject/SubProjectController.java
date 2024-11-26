package org.example.betasolutions.subProject;

import org.springframework.stereotype.Controller;

@Controller
public class SubProjectController {
    SubProjectService subProjectService;

    public SubProjectController(SubProjectService subProjectService){
        this.subProjectService = subProjectService;
    }

}
