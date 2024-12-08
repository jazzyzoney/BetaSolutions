package org.example.betasolutions.subTask;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository){
        this.subTaskRepository = subTaskRepository;
    }
    public List<SubTask> readAllSubTasks(int ProjectID, int TaskID){
        return subTaskRepository.readAllSubTasks(ProjectID, TaskID);

    }
}
