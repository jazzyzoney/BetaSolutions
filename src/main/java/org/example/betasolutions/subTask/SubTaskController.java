package org.example.betasolutions.subTask;

import org.springframework.stereotype.Controller;

@Controller
public class SubTaskController {
    SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService){
        this.subTaskService = subTaskService;
    }
}
