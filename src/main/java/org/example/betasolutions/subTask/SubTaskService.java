package org.example.betasolutions.subTask;

import org.springframework.stereotype.Service;

@Service
public class SubTaskService {
    private SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository){
        this.subTaskRepository = subTaskRepository;
    }
}
