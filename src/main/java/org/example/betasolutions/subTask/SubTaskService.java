package org.example.betasolutions.subTask;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository){
        this.subTaskRepository = subTaskRepository;
    }
    public void createSubTask(SubTask subTask){
        subTaskRepository.addSubTaskToTask(subTask);
    }

    public List<SubTask> readAllSubTasks(int ProjectID, int TaskID){
        return subTaskRepository.readAllSubTasks(ProjectID, TaskID);
    }
    public void deleteSubTask(int subTaskID){
        subTaskRepository.deleteSubTask(subTaskID);
    }
}
